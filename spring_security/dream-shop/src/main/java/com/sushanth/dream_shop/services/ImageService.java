package com.sushanth.dream_shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sushanth.dream_shop.Repositories.ImageRepository;
import com.sushanth.dream_shop.dtos.image.request.SaveImageRequest;
import com.sushanth.dream_shop.dtos.image.request.UpdateImageRequest;
import com.sushanth.dream_shop.dtos.image.response.DownloadImageResponse;
import com.sushanth.dream_shop.dtos.image.response.ImageResponse;
import com.sushanth.dream_shop.exceptions.ImageNotFoundException;
import com.sushanth.dream_shop.models.Image;
import com.sushanth.dream_shop.services.mappers.ImageMapper;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    public DownloadImageResponse getImageById(Integer id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Image with id " + id + " not found"));
        return imageMapper.imageToDownloadImageResponse(image);
    }

    public List<ImageResponse> getImageByProductId(Integer productId){
        List<ImageResponse> images = imageRepository.findByProductId(productId).stream().map(imageMapper::imageToImageResponse).toList();
        if( images.isEmpty() ){
            throw new ImageNotFoundException("Image with product id " + productId + " not found");
        }
        return images;
    }

    public void deleteImageById(Integer id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ImageNotFoundException("Image with id " + id + " not found");
        });
    }

    public ImageResponse saveImage(Integer productId, MultipartFile file) {
        SaveImageRequest saveImageRequest = new SaveImageRequest(file, productId);
        Image image = imageRepository.save(imageMapper.saveImageRequestToImage(saveImageRequest));
        image.setDownloadUrl("http://localhost:8080/images/download/" + image.getId());
        return imageMapper.imageToImageResponse(imageRepository.save(image));
    }

    public ImageResponse updateImage(UpdateImageRequest updateImageRequest) {
        Image image = imageRepository.findById(updateImageRequest.imageId())
                .map((existingImage) -> imageRepository
                        .save(imageMapper.updateImageRequestToImage(updateImageRequest, existingImage)))
                .orElseThrow(() -> new ImageNotFoundException("Image not found"));
        return imageMapper.imageToImageResponse(image);
    }
}
