package com.sushanth.dream_shop.dtos.product.response;

import java.math.BigDecimal;

public record ProductResponse(
    Integer id,
    String name,
    String brand,
    BigDecimal price,
    Integer inventory,
    String description,
    Integer categoryId
) {

}
