package com.example.productapi.dto;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Positive;

import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Positive;

public class PaymentDto {

    @NotNull//(message = "Order ID cannot be null")
    private Long orderId;

    @NotNull//(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull//(message = "Payment method cannot be null")
    private String paymentMethod;

    @NotNull//(message = "Payment details cannot be null")
    private PaymentDetailsDto paymentDetails;

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentDetailsDto getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetailsDto paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
