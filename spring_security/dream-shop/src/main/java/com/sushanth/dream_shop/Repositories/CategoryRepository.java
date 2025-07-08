package com.sushanth.dream_shop.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushanth.dream_shop.models.Category;


public interface CategoryRepository extends JpaRepository<Category , Integer> {
    Optional<Category> findByName(String name);
}
