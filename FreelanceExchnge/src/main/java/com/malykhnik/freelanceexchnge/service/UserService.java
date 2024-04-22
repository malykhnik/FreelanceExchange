package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.User;

public interface UserService {

    User findByUsername(String username);

    boolean saveUser(User user);

}
