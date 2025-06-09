package com.rest.rest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rest.rest.model.Student;
import com.rest.rest.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


record User(String name,int age) {
}

record Todos(int userId, int id, String title, boolean completed) {
}

@RestController
public class StudentController {

    @Autowired
    private StudentRepository sRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String helo() {
        return "<h1>Hello</h1>";
    }

    @GetMapping("/json")
    public ResponseEntity<List<User>> json(){
        return new ResponseEntity<>(
            List.of(
            new User("Sushanth", 19),
            new User("Sushanth", 19)
            ),HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Todos>> fetch(){
        RestTemplate restTemplate = new RestTemplate();
        Todos[] res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos", Todos[].class);
        List<Todos> response = Arrays.asList(res);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> adding(@RequestBody Student student) {
    
        return new ResponseEntity<>(sRepository.save(student),HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<List<Student>> findAll() {
        
        return new ResponseEntity<>(sRepository.findAll(),HttpStatus.OK);
    }

    @PostMapping("/student/{student_id}")
    public Student findById(@PathVariable int student_id) {
        
        return sRepository.findById(student_id).orElse(new Student());
    }
    
    @PostMapping("/students/search/{student_name}")
    public List<Student> findByName(@PathVariable String student_name) {
        
        return sRepository.findByFirstName(student_name);
    }
    
    @DeleteMapping("/student/{student_id}")
    public void deleteById(@PathVariable int student_id) {
        sRepository.deleteById(student_id);
    }
}
