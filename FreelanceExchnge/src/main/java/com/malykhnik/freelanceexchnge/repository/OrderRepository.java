package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN o.user u WHERE u.username = :username")
    List<Order> findOrdersByUsername(@Param("username") String username);

}
