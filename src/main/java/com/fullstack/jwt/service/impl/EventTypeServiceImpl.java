package com.fullstack.jwt.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.jwt.dto.EventTypeDto;
import com.fullstack.jwt.model.EventType;
import com.fullstack.jwt.repository.EventTypeRepository;
import com.fullstack.jwt.service.EventTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventTypeServiceImpl implements EventTypeService {

    private EventTypeRepository eventTypeRepository;
    private ObjectMapper objectMapper;

    @Override
    public List<EventTypeDto> getAllEventType() {

        List<EventType> industryList = eventTypeRepository.findAll();
        List<EventTypeDto> industryDtoList = new ArrayList<>();
        for (EventType industry : industryList) {
            EventTypeDto industryDto = objectMapper.convertValue(industry, EventTypeDto.class);
            industryDtoList.add(industryDto);
        }
        return industryDtoList;
    }
}
