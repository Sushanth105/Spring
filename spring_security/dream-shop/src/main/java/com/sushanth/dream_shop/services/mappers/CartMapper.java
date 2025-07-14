package com.sushanth.dream_shop.services.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushanth.dream_shop.dtos.cart.response.CartResponse;
import com.sushanth.dream_shop.dtos.cartItem.response.CartItemResponse;
import com.sushanth.dream_shop.models.Cart;

@Service
public class CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;

    public CartResponse cartToCartResponse(Cart cart){
        Set<CartItemResponse> cartItemResponses = cart.getCartItems().stream().map(cartItemMapper::cartItemToCartItemResponse).collect(Collectors.toSet());
        return new CartResponse(cart.getId(), cart.getTotalAmount(), cartItemResponses);
    }
}
