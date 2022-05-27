package com.safecornerscoffee.msa.user.feign;

import com.safecornerscoffee.msa.user.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/orders/{userId}/order1s")
    List<ResponseOrder> getOrders(@PathVariable String userId);
}
