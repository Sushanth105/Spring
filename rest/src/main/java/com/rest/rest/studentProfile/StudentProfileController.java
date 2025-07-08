package com.rest.rest.studentProfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @PostMapping("/studentProfile")
    public StudentProfileResponseDto create(@RequestBody StudentProfileRequestDto stdProReqDto){
        return studentProfileService.createStudentProfile(stdProReqDto);
    }

    @GetMapping("/studentProfiles")
    public ResponseEntity<?> getAll(){
        List<StudentProfileResponseDto> studentProfileResponseDtos = studentProfileService.getAllStudentProfileList();

        if(studentProfileResponseDtos.isEmpty()){
            return new ResponseEntity<>("There is no Student Profile found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentProfileResponseDtos,HttpStatus.OK);
    }
}
