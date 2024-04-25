package com.malykhnik.freelanceexchnge.service.impls;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.repository.OrderRepository;
import com.malykhnik.freelanceexchnge.repository.UserRepository;
import com.malykhnik.freelanceexchnge.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public void saveNewOrder(Order order) {
        String authorityName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()) != null) {
            User user = userRepository.findByUsername(authorityName);
            order.setUser(user);
        } else {
            throw new UsernameNotFoundException("Такого пользователя не существует!");
        }
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersByUsername(String username) {
        return orderRepository.findOrdersByUsername(username);
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
