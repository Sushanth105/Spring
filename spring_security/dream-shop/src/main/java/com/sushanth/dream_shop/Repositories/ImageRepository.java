package com.sushanth.dream_shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sushanth.dream_shop.models.Image;

import jakarta.transaction.Transactional;

public interface ImageRepository extends JpaRepository<Image , Integer> {
    @Transactional
    List<Image> findByProductId(@Param("productId") Integer productId);
}
