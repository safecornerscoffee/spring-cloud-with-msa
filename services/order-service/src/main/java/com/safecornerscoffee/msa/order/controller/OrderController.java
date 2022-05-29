package com.safecornerscoffee.msa.order.controller;

import com.safecornerscoffee.msa.order.entity.OrderEntity;
import com.safecornerscoffee.msa.order.queue.KafkaProducer;
import com.safecornerscoffee.msa.order.service.OrderService;
import com.safecornerscoffee.msa.order.vo.OrderDto;
import com.safecornerscoffee.msa.order.vo.RequestOrder;
import com.safecornerscoffee.msa.order.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder orderDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = modelMapper.map(createdOrder, ResponseOrder.class);

        kafkaProducer.send("product-topic", orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrdersByUserId(@PathVariable("userId") String userId) {
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseOrder.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
