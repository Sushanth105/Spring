package com.rest.rest.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rest.rest.school.School;

// @SpringBootTest
public class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void beforeEach(){
        studentMapper = new StudentMapper();
    }

    @Test
    void testToStudent() {
        StudentRequestDto studentRequestDto = new StudentRequestDto("Sushanth", "Ganiga", 19, "veenaganiga96@gmail.com", 1);
        Student student = studentMapper.toStudent(studentRequestDto);
        Assertions.assertEquals(studentRequestDto.firstName(), student.getFirstName());
        Assertions.assertEquals(studentRequestDto.lastName(), student.getLastName());
        Assertions.assertEquals(studentRequestDto.age(), student.getAge());
        Assertions.assertEquals(studentRequestDto.email(), student.getEmail());
        Assertions.assertNotNull(student.getSchool());
        Assertions.assertEquals(studentRequestDto.schoolId(), student.getSchool().getId());
    }

    @Test
    void findNullHandelling(){
        StudentRequestDto studentRequestDto = null;
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, ()->{
            studentMapper.toStudent(studentRequestDto);
        });
        Assertions.assertEquals(exception.getMessage(), "The StudentDto should not be null");
    }

    @Test
    void testToStudentDto() {
        School school = new School();
        school.setId(1);
        Student student = new Student("Sushanth","Ganiga","veenaganiga96@gmail.com",19,school);
        StudentResponseDto studentResponseDto = studentMapper.toStudentDto(student);
        Assertions.assertEquals(student.getFirstName(), studentResponseDto.firstName());
        Assertions.assertEquals(student.getLastName(), studentResponseDto.lastName());
        Assertions.assertEquals(student.getAge(), studentResponseDto.age());
        Assertions.assertEquals(student.getEmail(), studentResponseDto.email());
        Assertions.assertEquals(student.getSchool().getId(), studentResponseDto.schoolId());
    }
}
