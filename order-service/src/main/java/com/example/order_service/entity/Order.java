package com.example.order_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private String status;

    public Order(String customerId, Long id, Long productId, int quantity, String status, double totalPrice) {
        super();
        this.customerId = customerId;
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order() {
        super();
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
