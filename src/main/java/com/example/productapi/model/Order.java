package com.example.productapi.model;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.time.LocalDateTime;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Table(name = "customer_order")  // Renamed from `order`
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime orderDate;

    public void setProductId(Long productId) {

        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {

        this.totalPrice = totalPrice;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // Getters and Setters
}
