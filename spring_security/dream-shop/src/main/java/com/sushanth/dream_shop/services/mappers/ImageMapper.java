package com.sushanth.dream_shop.services.mappers;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sushanth.dream_shop.dtos.image.request.SaveImageRequest;
import com.sushanth.dream_shop.dtos.image.request.UpdateImageRequest;
import com.sushanth.dream_shop.dtos.image.response.DownloadImageResponse;
import com.sushanth.dream_shop.dtos.image.response.ImageResponse;
import com.sushanth.dream_shop.models.Image;
import com.sushanth.dream_shop.models.Product;

@Service
public class ImageMapper {
    public Image saveImageRequestToImage(SaveImageRequest saveImageRequest) {
        Product product = new Product();
        product.setId(saveImageRequest.productId());

        Image image = null;
        try {
            image = new Image(saveImageRequest.file().getOriginalFilename(), saveImageRequest.file().getContentType(),
                    saveImageRequest.file().getBytes(), null, product);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return image;
    }

    public Image updateImageRequestToImage(UpdateImageRequest updateImageRequest, Image image) {
        MultipartFile file = updateImageRequest.file();
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        try {
            image.setImage(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        image.setFileName(file.getOriginalFilename());
        return image;
    }

    public DownloadImageResponse imageToDownloadImageResponse(Image image) {
        return new DownloadImageResponse(image.getId(), image.getFileName(), image.getFileType(), image.getImage(),
                image.getDownloadUrl(), image.getProduct().getId());
    }

    public ImageResponse imageToImageResponse(Image image) {
        return new ImageResponse(image.getId(), image.getFileName(), image.getFileType(),
                image.getDownloadUrl(), image.getProduct().getId());
    }
}
