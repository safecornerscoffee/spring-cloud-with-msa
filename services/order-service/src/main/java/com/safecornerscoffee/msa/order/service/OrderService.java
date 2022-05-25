package com.safecornerscoffee.msa.order.service;

import com.safecornerscoffee.msa.order.entity.OrderEntity;
import com.safecornerscoffee.msa.order.repository.OrderRepository;
import com.safecornerscoffee.msa.order.vo.OrderDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getUnitPrice()*orderDetails.getUnitPrice());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderEntity orderEntity = modelMapper.map(orderDetails, OrderEntity.class);

        orderRepository.save(orderEntity);

        return modelMapper.map(orderEntity, OrderDto.class);
    }

    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderEntity, OrderDto.class);
    }

    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findAll();
    }
}
