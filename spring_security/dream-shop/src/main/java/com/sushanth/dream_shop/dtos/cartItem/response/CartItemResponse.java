package com.sushanth.dream_shop.dtos.cartItem.response;

import java.math.BigDecimal;

import com.sushanth.dream_shop.dtos.product.response.ProductResponse;

public record CartItemResponse(
    Integer id,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice,
    ProductResponse product,
    Integer cartId
) {

}
