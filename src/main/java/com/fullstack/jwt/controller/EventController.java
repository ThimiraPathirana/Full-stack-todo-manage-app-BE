package com.fullstack.jwt.controller;

import com.fullstack.jwt.dto.EventTypeDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    private List<EventTypeDto> typeDtos = new ArrayList<>(List.of(
            new EventTypeDto(1, "Music", "Musical Event"),
            new EventTypeDto(2, "Meetup", "Educational Meetup")
    ));

    @GetMapping("/")
    public String HelloEvent(HttpServletRequest request) {
        return "Welcome first event! " + request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/event")
    public List<EventTypeDto> getEventTypes(HttpServletRequest request) {
        return typeDtos;
    }

    @PostMapping("/event/add")
    public List<EventTypeDto> addEventType(EventTypeDto eventTypeDto) {
        typeDtos.add(eventTypeDto);
        return typeDtos;
    }
}
