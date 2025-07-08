package com.sushanth.dream_shop.dtos.image.response;

public record ImageResponse(
    Integer id,
    String fileName,
    String fileType,
    String downloadUrl,
    Integer productId
) {

}
