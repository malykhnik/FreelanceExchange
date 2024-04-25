package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void saveNewOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUsername(String username);
    Optional<Order> findOrderById(Long id);
    void deleteOrderById(Long id);
}
