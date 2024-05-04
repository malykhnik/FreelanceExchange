package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.User;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

}
