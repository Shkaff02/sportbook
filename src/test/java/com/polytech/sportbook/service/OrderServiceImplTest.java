package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Order;
import com.polytech.sportbook.repo.OrderRepository;
import com.polytech.sportbook.repo.SportObjectRepository;
import com.polytech.sportbook.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderService = new OrderServiceImpl(orderRepository, userRepository, sportObjectRepository);
    }

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SportObjectRepository sportObjectRepository;

    @Test
    public void saveOrder(){
        Long obj_id = 1L;
        Long user_id = 2L;
        Order order = new Order();
        orderService.saveOrder(obj_id, user_id, order);
        Mockito.verify(userRepository).findById(user_id);
        Mockito.verify(sportObjectRepository).findById(obj_id);
        Mockito.verify(orderRepository).save(order);
    }

    @Test
    public void getOrderById(){
        Long ord_id = 1L;
        orderService.getOrderById(ord_id);
        Mockito.verify(orderRepository).findById(ord_id);
    }

    @Test
    public void getOrdersTest(){
        orderService.orders();
        Mockito.verify(orderRepository).findAll();
    }

    @Test
    public void deleteOrderTest(){
        Long ord_id = 3L;
        orderService.deleteOrder(ord_id);
        Mockito.verify(orderRepository).deleteById(ord_id);
    }

    @Test
    public void cancelOrderTest(){
        Long ord_id = 4L;
        Order order = null;
        orderService.cancelOrder(ord_id);
        Mockito.verify(orderRepository).findById(ord_id);
        Mockito.verify(orderRepository).save(order);
    }

    @Test
    public void payOrderTest(){
        Long ord_id = 2L;
        Order order = null;
        orderService.payOrder(ord_id);
        Mockito.verify(orderRepository).findById(ord_id);
        Mockito.verify(orderRepository).save(order);
    }
}
