package com.coading.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coading.jpa.models.Video;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    
}
