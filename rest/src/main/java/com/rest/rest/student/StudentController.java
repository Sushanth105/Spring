package com.rest.rest.student;

import org.springframework.web.client.RestTemplate;

import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.MethodArgumentNotValidException;

record User(String name, int age) {
}

record Todos(int userId, int id, String title, boolean completed) {
}

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String helo() {
        return "<h1>Hello</h1>";
    }

    @GetMapping("/json")
    public ResponseEntity<List<User>> json() {
        return new ResponseEntity<>(
                List.of(
                        new User("Sushanth", 19),
                        new User("Sushanth", 19)),
                HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Todos>> fetch() {
        RestTemplate restTemplate = new RestTemplate();
        Todos[] res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos", Todos[].class);
        List<Todos> response = Arrays.asList(res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<StudentResponseDto> adding( @Valid @RequestBody StudentRequestDto studentDto) {

        return new ResponseEntity<>(studentService.saveStudent(studentDto),HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> findAll() {

        List<StudentResponseDto> students = studentService.findStudentAll();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/student/{student_id}")
    public ResponseEntity<?> findById(@PathVariable int student_id) {

        var student = studentService.findStudentById(student_id);

        if(student.isEmpty()){
            return new ResponseEntity<>("There is no Student with student id " + student_id,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @GetMapping("/students/search/{student_name}")
    public ResponseEntity<?> findByName(@PathVariable String student_name) {
        List<StudentResponseDto> students = studentService.findStudentByName(student_name);
        if(students.isEmpty()){
            return new ResponseEntity<>("There is no data with student name " + student_name, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @DeleteMapping("/student/{student_id}")
    public ResponseEntity<String>  deleteById(@PathVariable Integer student_id) {
        studentService.deleteStudentById(student_id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exe){
        HashMap<String , String> error = new HashMap<>();
        exe.getAllErrors().forEach((e) ->{
            var fieldName = ((FieldError) e).getField();
            var message = e.getDefaultMessage();
            error.put(fieldName, message);
        });
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
