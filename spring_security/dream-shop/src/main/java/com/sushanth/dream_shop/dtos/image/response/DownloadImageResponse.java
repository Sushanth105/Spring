package com.sushanth.dream_shop.dtos.image.response;

public record DownloadImageResponse(
    Integer id,
    String fileName,
    String fileType,
    byte[] image,
    String downloadUrl,
    Integer productId
) {

}
