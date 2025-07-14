package com.sushanth.dream_shop.dtos.cart.response;

import java.math.BigDecimal;
import java.util.Set;

import com.sushanth.dream_shop.dtos.cartItem.response.CartItemResponse;

public record CartResponse(
    Integer id,
    BigDecimal totalAmount,
    Set<CartItemResponse> cartItems
) {

}
