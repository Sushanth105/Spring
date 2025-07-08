package com.rest.rest.student;

import org.springframework.stereotype.Service;

import com.rest.rest.school.School;

@Service
public class StudentMapper {

    public StudentResponseDto toStudentDto(Student student) {
        return new StudentResponseDto(student.getId(), student.getFirstName(), student.getLastName(), student.getAge(),
                student.getEmail(), student.getSchool().getId());
    }

    public Student toStudent(StudentRequestDto studentDto) {
        if(studentDto == null){
            throw new NullPointerException("The StudentDto should not be null");
        }
        School school = new School();
        school.setId(studentDto.schoolId());
        return new Student(studentDto.firstName(), studentDto.lastName(), studentDto.email(), studentDto.age(), school);
    }
}
