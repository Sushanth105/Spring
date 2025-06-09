package com.rest.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.rest.model.Student;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByFirstName(String firstName);
}
