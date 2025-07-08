package com.coading.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coading.jpa.models.Lecture;

public interface LectureRepository extends JpaRepository<Lecture,Integer> {

}
