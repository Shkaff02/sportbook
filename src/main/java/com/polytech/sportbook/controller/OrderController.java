package com.polytech.sportbook.controller;

import com.polytech.sportbook.domain.Order;
import com.polytech.sportbook.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok().body(orderService.orders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order, @RequestParam Long obj_id, @RequestParam Long user_id) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/orders").toUriString());
        return ResponseEntity.created(uri).body(orderService.saveOrder(obj_id, user_id, order));
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.cancelOrder(id));
    }

    @PatchMapping("/orders/payment/{id}")
    public ResponseEntity<Order> payOrder(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.payOrder(id));
    }


}

