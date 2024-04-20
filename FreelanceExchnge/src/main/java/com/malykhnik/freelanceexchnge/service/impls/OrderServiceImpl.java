package com.malykhnik.freelanceexchnge.service.impls;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    public void saveNewOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }
}
