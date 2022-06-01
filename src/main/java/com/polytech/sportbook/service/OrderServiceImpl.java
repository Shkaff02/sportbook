package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Order;
import com.polytech.sportbook.domain.PAY_STATUS;
import com.polytech.sportbook.domain.RESERVE_STATUS;
import com.polytech.sportbook.repo.OrderRepository;
import com.polytech.sportbook.repo.SportObjectRepository;
import com.polytech.sportbook.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SportObjectRepository sportObjectRepository;


    @Override
    public Order saveOrder(Long obj_id, Long user_id, Order order) {
        log.info("Saving order with id {} to database", order.getId());
        order.setUser(userRepository.findById(user_id).orElse(null));
        order.setObject(sportObjectRepository.findById(obj_id).orElse(null));
        order.setDateOfCreation(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        log.info("Fetching order with id {} from database", id);
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> orders() {
        log.info("Fetching all orders from database");
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        log.info("Deleting order with id {}", id);
        orderRepository.deleteById(id);
    }

    @Override
    public Order cancelOrder(Long id) {
        log.info("Canceling order with id {}", id);
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setReserveStatus(RESERVE_STATUS.RESERVE_CANCELED);
            if (order.getPayStatus() == PAY_STATUS.IN_PROCESS || order.getPayStatus() == PAY_STATUS.PAYED) {
                order.setPayStatus(PAY_STATUS.PAYMENT_CANCELED);
            }
        }

        return orderRepository.save(order);
    }

    @Override
    public Order payOrder(Long id) {
        log.info("Paying order with id {}", id);
        Order order = orderRepository.findById(id).orElse(null);
        if(order != null) {
            order.setReserveStatus(RESERVE_STATUS.BOOKED);
            order.setPayStatus(PAY_STATUS.PAYED);
        }
        order.setDateOfPayment(new Date());

        return orderRepository.save(null);
    }
}
