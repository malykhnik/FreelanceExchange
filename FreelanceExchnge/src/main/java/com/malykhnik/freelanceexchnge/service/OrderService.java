package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.Order;

import java.util.List;

public interface OrderService {
    void saveNewOrder(Order order);
    List<Order> getAllOrders();
}
