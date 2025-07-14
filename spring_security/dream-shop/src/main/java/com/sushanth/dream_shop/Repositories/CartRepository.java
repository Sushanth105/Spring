package com.sushanth.dream_shop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushanth.dream_shop.models.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}
