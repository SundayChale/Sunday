package com.example.productapi.controller;

import com.example.productapi.dto.PaymentDto;
import com.example.productapi.model.Payment;
import com.example.productapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/payments")
@Validated
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setOrderId(paymentDto.getOrderId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setCardNumber(paymentDto.getPaymentDetails().getCardNumber());
        payment.setExpiryDate(paymentDto.getPaymentDetails().getExpiryDate());
        payment.setCvv(paymentDto.getPaymentDetails().getCvv());
        payment.setStatus("pending"); // Default status, to be updated later based on payment processing
        payment.setTimestamp(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentStatus(@PathVariable Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> ResponseEntity.ok().body(payment))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
