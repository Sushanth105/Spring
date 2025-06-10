package com.rest.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rest.rest.model.School;
import com.rest.rest.repository.SchoolRepository;
import com.rest.rest.dto.SchoolRequestDto;
import com.rest.rest.dto.SchoolResponseDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    public SchoolResponseDto toSchoolDto(School school) {
        return new SchoolResponseDto(school.getId(), school.getName());
    }

    public School toSchool(SchoolRequestDto schoolDto) {
        return new School(schoolDto.name());
    }

    @PostMapping("/school")
    public SchoolResponseDto create(@RequestBody SchoolRequestDto schoolRequestDto) {
        
        return toSchoolDto(schoolRepository.save(toSchool(schoolRequestDto)));
    }

    @PostMapping("/schools")
    public ResponseEntity<?> getAllSchool() {
        List<SchoolResponseDto> schools = schoolRepository.findAll().stream().map(this::toSchoolDto).toList();
        if(schools.isEmpty()){
            return new ResponseEntity<>("No School",HttpStatus.OK);
        }
        return new ResponseEntity<>(schools,HttpStatus.OK);
    }
    
}
