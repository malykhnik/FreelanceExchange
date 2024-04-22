package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
