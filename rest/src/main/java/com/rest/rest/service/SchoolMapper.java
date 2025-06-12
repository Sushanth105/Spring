package com.rest.rest.service;

import org.springframework.stereotype.Service;

import com.rest.rest.dto.SchoolRequestDto;
import com.rest.rest.dto.SchoolResponseDto;
import com.rest.rest.model.School;

@Service
public class SchoolMapper {

    public SchoolResponseDto toSchoolDto(School school) {
        return new SchoolResponseDto(school.getId(), school.getName());
    }

    public School toSchool(SchoolRequestDto schoolDto) {
        return new School(schoolDto.name());
    }
}
