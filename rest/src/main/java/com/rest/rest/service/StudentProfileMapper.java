package com.rest.rest.service;

import org.springframework.stereotype.Service;

import com.rest.rest.dto.StudentProfileRequestDto;
import com.rest.rest.dto.StudentProfileResponseDto;
import com.rest.rest.model.Student;
import com.rest.rest.model.StudentProfile;

@Service
public class StudentProfileMapper {

    public StudentProfileResponseDto toStudentProfileDto(StudentProfile studentProfile){
        return new StudentProfileResponseDto(studentProfile.getId(),studentProfile.getBio(),studentProfile.getStudent().getId());
    }

    public StudentProfile toStudentProfile(StudentProfileRequestDto studentProfileRequestDto){
        Student student = new Student();
        student.setId(studentProfileRequestDto.studentId());
        return new StudentProfile(studentProfileRequestDto.bio(), student);
    }
}
