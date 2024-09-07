package com.fullstack.jwt.service;

import com.fullstack.jwt.model.User;

public interface UserService {

    /**
     * When we pass user obj new user will be created
     * @param user
     * @return created user
     */
    User registerUser(User user);
}
