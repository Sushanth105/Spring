package com.rest.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.rest.model.School;

public interface SchoolRepository extends JpaRepository<School,Integer> {

}
