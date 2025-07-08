package com.rest.rest.studentProfile;

import org.springframework.stereotype.Service;

import com.rest.rest.student.Student;

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
