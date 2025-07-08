package com.rest.rest.student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository sRepository;

    @Autowired
    private StudentMapper studentMapper;

    public StudentResponseDto saveStudent(StudentRequestDto studentDto){
        Student student = sRepository.save(studentMapper.toStudent(studentDto));
        return studentMapper.toStudentDto(student);
    }

    public Optional<StudentResponseDto> findStudentById(int student_id){
        var student = sRepository.findById(student_id);

        if (student.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(studentMapper.toStudentDto(student.get()));
    }

    public List<StudentResponseDto> findStudentByName(String student_name){
        List<StudentResponseDto> students = sRepository.findByFirstName(student_name).stream().map(studentMapper::toStudentDto).toList();

        return students;
    }

    public List<StudentResponseDto> findStudentAll() {

        List<StudentResponseDto> students = sRepository.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());

        return students;
    }

    public void deleteStudentById(int student_id){
        sRepository.deleteById(student_id);
    }
}
