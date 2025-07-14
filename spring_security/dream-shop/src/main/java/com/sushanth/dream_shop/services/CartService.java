package com.sushanth.dream_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushanth.dream_shop.Repositories.CartItemRepository;
import com.sushanth.dream_shop.Repositories.CartRepository;
import com.sushanth.dream_shop.dtos.cart.response.CartResponse;
import com.sushanth.dream_shop.exceptions.CartNotFoundException;
import com.sushanth.dream_shop.models.Cart;
import com.sushanth.dream_shop.services.mappers.CartMapper;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @SuppressWarnings("unused")
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartMapper cartMapper;

    public CartResponse getCart(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("cart with id " + id + " not found"));
        cart.updateTotalAmount();
        return cartMapper.cartToCartResponse(cartRepository.save(cart));
    }

    // public CartResponse clearCart(Integer id){
    // cartItemRepository.deleteAllByCartId(id);
    // return getCart(id);
    // }

    // or...

    public CartResponse clearCart(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("cart with id " + id + " not found"));
        cart.getCartItems().clear();
        cart.updateTotalAmount();
        return cartMapper.cartToCartResponse(cartRepository.save(cart));
    }

    
}
