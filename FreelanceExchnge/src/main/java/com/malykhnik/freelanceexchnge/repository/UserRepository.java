package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
