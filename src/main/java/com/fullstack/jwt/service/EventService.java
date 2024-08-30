package com.fullstack.jwt.service;

import com.fullstack.jwt.dto.EventTypeDto;

import java.util.List;

public interface EventService {

    List<EventTypeDto> getAllEvent();
}
