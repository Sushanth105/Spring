package com.rest.rest.school;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/school")
    public SchoolResponseDto create(@RequestBody SchoolRequestDto schoolRequestDto) {
        
        return schoolService.createSchool(schoolRequestDto);
    }

    @GetMapping("/schools")
    public ResponseEntity<?> getAllSchool() {
        List<SchoolResponseDto> schools = schoolService.getAllStudentList();
        if(schools.isEmpty()){
            return new ResponseEntity<>("No School",HttpStatus.OK);
        }
        return new ResponseEntity<>(schools,HttpStatus.OK);
    }

    @DeleteMapping("/school/{id}")
    public String deleteById(@PathVariable int id){
        schoolService.deleteSchoolById(id);
        return "School is deleted";
    }
    
}
