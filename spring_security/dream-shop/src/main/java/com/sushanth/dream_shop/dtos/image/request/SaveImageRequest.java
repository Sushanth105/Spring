package com.sushanth.dream_shop.dtos.image.request;

import org.springframework.web.multipart.MultipartFile;

public record SaveImageRequest(
    MultipartFile file,
    Integer productId
) {

}
