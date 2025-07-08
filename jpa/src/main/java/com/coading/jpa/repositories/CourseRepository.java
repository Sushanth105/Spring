package com.coading.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coading.jpa.models.Course;

public interface CourseRepository extends JpaRepository<Course , Integer> {

}
