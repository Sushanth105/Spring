package com.sushanth.dream_shop.dtos.image.request;

import org.springframework.web.multipart.MultipartFile;

public record UpdateImageRequest(
    MultipartFile file,
    Integer imageId
) {

}
