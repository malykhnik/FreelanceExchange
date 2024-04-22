package com.malykhnik.freelanceexchnge.service.impls;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.repository.OrderRepository;
import com.malykhnik.freelanceexchnge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public void saveNewOrder(Order order) {
        String authorityName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.findByUsername(authorityName) != null) {
            User user = userRepository.findByUsername(authorityName);
            order.setUser(user);
        } else {
            throw new UsernameNotFoundException("Такого пользователя не существует!");
        }
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }
}
