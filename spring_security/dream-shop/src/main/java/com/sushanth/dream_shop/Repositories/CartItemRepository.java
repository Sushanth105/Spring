package com.sushanth.dream_shop.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushanth.dream_shop.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    void deleteAllByCartId(Integer Id);

    Optional<CartItem> findByCartIdAndProductId(Integer cartId,Integer productId);
}
