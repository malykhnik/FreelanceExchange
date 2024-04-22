package com.malykhnik.freelanceexchnge.service.impls;


import com.malykhnik.freelanceexchnge.model.Role;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.repository.RoleRepository;
import com.malykhnik.freelanceexchnge.repository.UserRepository;
import com.malykhnik.freelanceexchnge.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    public void saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return;
        }

        Role role = new Role("NO_ROLE");
        roleRepository.save(role);
        user.setRole(role);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}