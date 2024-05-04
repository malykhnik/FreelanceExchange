package com.malykhnik.freelanceexchnge.service.impls;


import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.repository.UserRepository;
import com.malykhnik.freelanceexchnge.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return;
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}