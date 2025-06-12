package com.rest.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.rest.model.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Integer> {

}
