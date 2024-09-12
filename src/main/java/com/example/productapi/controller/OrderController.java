package com.example.productapi.controller;

import com.example.productapi.dto.OrderDto;
import com.example.productapi.model.Order;
import com.example.productapi.repository.OrderRepository;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        // Validate product and calculate total price
        return productRepository.findById(orderDto.getProductId())
                .map(product -> {
                    double totalPrice = product.getPrice() * orderDto.getQuantity();

                    Order order = new Order();
                    order.toString();
                    order.setProductId(orderDto.getProductId());
                    order.setQuantity(orderDto.getQuantity());
                    order.setTotalPrice(totalPrice);
                    order.setOrderDate(LocalDateTime.now());

                    Order savedOrder = orderRepository.save(order);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orders);
        }
    }
}
