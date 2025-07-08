package com.sushanth.dream_shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushanth.dream_shop.models.Product;

public interface ProductRepository extends JpaRepository<Product , Integer> {

    List<Product> findByCategoryName(String name);
    List<Product> findByBrand(String brand);
    List<Product> findByCategoryNameAndBrand(String categoryName, String brand);
    List<Product> findByName(String name);
    List<Product> findByBrandAndName(String brand, String name);
    Integer countByBrandAndName(String brand, String name);
}
