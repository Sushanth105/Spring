package com.rest.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rest.rest.model.School;
import com.rest.rest.repository.SchoolRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping("/school")
    public School create(@RequestBody School school) {
        
        return schoolRepository.save(school);
    }

    @PostMapping("/schools")
    public List<School> getAllSchool() {
        
        return schoolRepository.findAll();
    }
    
}
