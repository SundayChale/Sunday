package com.example.productapi.repository;

import com.example.productapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // You can add custom queries if needed
}
