package com.rest.rest.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public SchoolResponseDto toSchoolDto(School school) {
        return new SchoolResponseDto(school.getId(), school.getName());
    }

    public School toSchool(SchoolRequestDto schoolDto) {
        return new School(schoolDto.name());
    }
}
