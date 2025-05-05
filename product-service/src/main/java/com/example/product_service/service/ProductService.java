package com.example.product_service.service;

import java.util.List;

import com.example.product_service.exception.ProductException;
import org.springframework.stereotype.Service;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;

@Service
public class ProductService {
	
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException("Produit introuvable avec l'ID : " + id));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

}
