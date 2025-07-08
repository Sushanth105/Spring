package com.sushanth.dream_shop.dtos.product.request;

import java.math.BigDecimal;

public record AddProductRequest(
    String name,
    String brand,
    BigDecimal price,
    Integer inventory,
    String description,
    String categoryName
) {

}
