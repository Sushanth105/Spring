package com.coading.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.coading.jpa.models.Author;

import jakarta.transaction.Transactional;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Author a SET a.age = :age where a.id = :id")
    public void updateById(Integer id , Integer age);

}
