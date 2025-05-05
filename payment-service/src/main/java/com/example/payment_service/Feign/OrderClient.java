package com.example.payment_service.Feign;


import com.example.payment_service.DTO.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public interface OrderClient {
    @GetMapping("/api/order/{id}")
    Order getOrderById(@PathVariable("id") Long id);
}
