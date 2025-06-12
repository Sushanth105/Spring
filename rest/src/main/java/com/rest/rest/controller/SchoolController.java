package com.rest.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rest.rest.repository.SchoolRepository;
import com.rest.rest.service.SchoolMapper;
import com.rest.rest.dto.SchoolRequestDto;
import com.rest.rest.dto.SchoolResponseDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolMapper schoolMapper;

    @PostMapping("/school")
    public SchoolResponseDto create(@RequestBody SchoolRequestDto schoolRequestDto) {
        
        return schoolMapper.toSchoolDto(schoolRepository.save(schoolMapper.toSchool(schoolRequestDto)));
    }

    @PostMapping("/schools")
    public ResponseEntity<?> getAllSchool() {
        List<SchoolResponseDto> schools = schoolRepository.findAll().stream().map(schoolMapper::toSchoolDto).toList();
        if(schools.isEmpty()){
            return new ResponseEntity<>("No School",HttpStatus.OK);
        }
        return new ResponseEntity<>(schools,HttpStatus.OK);
    }

    @DeleteMapping("/school/{id}")
    public void deleteById(@PathVariable int id){
        schoolRepository.deleteById(id);
    }
    
}
