package com.fullstack.jwt.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.jwt.dto.IndustryDto;
import com.fullstack.jwt.model.Industry;
import com.fullstack.jwt.repository.IndustryRepository;
import com.fullstack.jwt.service.IndustryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndustryServiceImpl implements IndustryService {

    private IndustryRepository industryRepository;
    private ObjectMapper objectMapper;

    @Override
    public List<IndustryDto> getAllIndustry() {

        List<Industry> industryList = industryRepository.findAll();
        List<IndustryDto> industryDtoList = new ArrayList<>();
        for (Industry industry : industryList) {
            IndustryDto industryDto = objectMapper.convertValue(industry, IndustryDto.class);
            industryDtoList.add(industryDto);
        }
        return industryDtoList;
    }
}
