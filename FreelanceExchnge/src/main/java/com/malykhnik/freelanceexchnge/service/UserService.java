package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

}
