package com.rest.rest.service;

import org.springframework.stereotype.Service;

import com.rest.rest.dto.StudentRequestDto;
import com.rest.rest.dto.StudentResponseDto;
import com.rest.rest.model.School;
import com.rest.rest.model.Student;

@Service
public class StudentMapper {

    public StudentResponseDto toStudentDto(Student student) {
        return new StudentResponseDto(student.getId(), student.getFirstName(), student.getLastName(), student.getAge(),
                student.getEmail(), student.getSchool().getId());
    }

    public Student toStudent(StudentRequestDto studentDto) {
        School school = new School();
        school.setId(studentDto.schoolId());
        return new Student(studentDto.firstName(), studentDto.lastName(), studentDto.email(), studentDto.age(), school);
    }
}
