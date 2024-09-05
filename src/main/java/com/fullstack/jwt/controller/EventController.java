package com.fullstack.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @GetMapping("/")
    public String HelloEvent(HttpServletRequest request) {
        return "Welcome first event! " + request.getSession().getId();
    }
}
