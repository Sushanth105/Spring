package com.rest.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.rest.dto.StudentProfileRequestDto;
import com.rest.rest.dto.StudentProfileResponseDto;
import com.rest.rest.repository.StudentProfileRepository;
import com.rest.rest.service.StudentProfileMapper;

@RestController
public class StudentProfileController {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private StudentProfileMapper studentProfileMapper;

    @PostMapping("/studentProfile")
    public StudentProfileResponseDto create(@RequestBody StudentProfileRequestDto stdProReqDto){
        return studentProfileMapper.toStudentProfileDto(studentProfileRepository.save(studentProfileMapper.toStudentProfile(stdProReqDto)));
    }

    @PostMapping("/studentProfiles")
    public ResponseEntity<?> getAll(){
        List<StudentProfileResponseDto> studentProfileResponseDtos = studentProfileRepository.findAll().stream().map(studentProfileMapper::toStudentProfileDto).toList();

        if(studentProfileResponseDtos.isEmpty()){
            return new ResponseEntity<>("There is no Student Profile found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentProfileResponseDtos,HttpStatus.OK);
    }
}
