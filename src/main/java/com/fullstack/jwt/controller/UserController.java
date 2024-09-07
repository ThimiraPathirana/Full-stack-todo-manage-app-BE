package com.fullstack.jwt.controller;

import com.fullstack.jwt.model.User;
import com.fullstack.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * We are use BCryptPasswordEncoder with strength 12
     */
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//    /**
//     * We are use BCryptPasswordEncoder with BCryptVersion 2a
//     */
//    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);

    @PostMapping("/register")
    public User registerUser(User user) {
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        return userService.registerUser(user);
    }
}
