package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
