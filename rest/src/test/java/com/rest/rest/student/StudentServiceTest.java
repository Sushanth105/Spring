package com.rest.rest.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rest.rest.school.School;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private StudentRepository studentRepository;

    private StudentRequestDto studentRequestDto;
    private StudentResponseDto studentResponseDto;
    private Student student;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
        studentRequestDto = new StudentRequestDto("Sushanth", "Ganiga", 19, "veenaganiga96@gmail.com", 1);
        studentResponseDto = new StudentResponseDto(1, "Sushanth", "Ganiga", 19, "veenagaiga96@gmail.com", 1);
        School school = new School();
        school.setId(1);
        student = new Student("Sushanth", "Ganiga", "veenaganiga96@gmail.com", 19, school);
    }

    @Test
    void testDeleteStudentById() {

    }

    @Test
    void testFindStudentAll() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
        when(studentMapper.toStudentDto(student)).thenReturn(studentResponseDto);

        List<StudentResponseDto> result = studentService.findStudentAll();

        assertEquals(1, result.size());
    }

    @Test
    void testFindStudentById() {

    }

    @Test
    void testFindStudentByName() {

    }

    @Test
    void testSaveStudent() {
        Mockito.when(studentMapper.toStudent(studentRequestDto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when(studentMapper.toStudentDto(student)).thenReturn(studentResponseDto);

        StudentResponseDto result = studentService.saveStudent(studentRequestDto);

        Assertions.assertEquals(result.id(), studentResponseDto.id());
        Assertions.assertEquals(result.firstName(), studentResponseDto.firstName());
        Assertions.assertEquals(result.lastName(), studentResponseDto.lastName());
        Assertions.assertEquals(result.age(), studentResponseDto.age());
        Assertions.assertEquals(result.email(), studentResponseDto.email());
        Assertions.assertEquals(result.schoolId(), studentResponseDto.schoolId());

        verify(studentMapper , times(1)).toStudent(studentRequestDto);
        verify(studentMapper , times(1)).toStudentDto(student);
        verify(studentRepository , times(1)).save(student);
    }
}
