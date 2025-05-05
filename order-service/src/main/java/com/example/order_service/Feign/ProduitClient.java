package com.example.order_service.Feign;


import com.example.order_service.DTO.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProduitClient {
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
