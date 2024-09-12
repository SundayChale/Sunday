package com.example.productapi.dto;

public class OrderDto {
    private Long userId;
    private Long productId;
    private Integer quantity;

    public Long getProductId() {

        return productId;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public Object getUserId() {

        return userId;
    }

    // Getters and Setters
}
