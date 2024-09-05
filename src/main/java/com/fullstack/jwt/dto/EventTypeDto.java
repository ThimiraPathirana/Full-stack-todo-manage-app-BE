package com.fullstack.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventTypeDto {

    Integer id;
    String name;
    String description;

    public EventTypeDto(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
