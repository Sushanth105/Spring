package com.sushanth.dream_shop.dtos.product.response;

import java.math.BigDecimal;
import java.util.List;

import com.sushanth.dream_shop.dtos.category.response.CategoryResponse;
import com.sushanth.dream_shop.dtos.image.response.ImageResponse;

public record ProductResponse(
    Integer id,
    String name,
    String brand,
    BigDecimal price,
    Integer inventory,
    String description,
    CategoryResponse category,
    List<ImageResponse> image
) {
}
