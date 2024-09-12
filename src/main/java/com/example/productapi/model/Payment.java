package com.example.productapi.model;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.time.LocalDateTime;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Table(name = "payment")  // Renamed from `order`
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long productId;
    private Double amount;
    private String paymentMethod; // e.g., 'Credit Card', 'PayPal'
    private String status; // e.g., 'Completed', 'Pending', 'Failed'
    private LocalDateTime createdAt;
    private LocalDateTime createsdAt;

    public void getClass(Object productId) {

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {



        this.createdAt = createdAt;
    }

    public void setCreatesdAt(LocalDateTime createsdAt) {

        this.createsdAt = createsdAt;
    }

    public void setOrderId(Long orderId) {

    }

    public void setAmount(Double amount) {

    }

    public void setPaymentMethod(String paymentMethod) {

    }

    public void setCardNumber(String cardNumber) {

    }

    public void setExpiryDate(String expiryDate) {

    }

    public void setCvv(String cvv) {

    }

    public void setTimestamp(LocalDateTime now) {

    }

    // Getters and Setters
}
