package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Long obj_id, Long user_id, Order order);

    Order getOrderById(Long id);

    List<Order> orders();

    void deleteOrder(Long id);

    Order cancelOrder(Long id);

    Order payOrder(Long id);
}
