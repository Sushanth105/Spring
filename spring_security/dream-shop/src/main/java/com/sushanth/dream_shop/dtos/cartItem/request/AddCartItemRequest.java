package com.sushanth.dream_shop.dtos.cartItem.request;

public record AddCartItemRequest(
    Integer cartId,
    Integer productId,
    Integer quantity
) {

}
