package com.sushanth.dream_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sushanth.dream_shop.dtos.image.request.UpdateImageRequest;
import com.sushanth.dream_shop.dtos.image.response.DownloadImageResponse;
import com.sushanth.dream_shop.dtos.image.response.ImageResponse;
import com.sushanth.dream_shop.response.ResponseApi;
import com.sushanth.dream_shop.services.ImageService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("")
    public ResponseEntity<ResponseApi> saveIamge(@RequestParam Integer productId, @RequestPart MultipartFile file) {
        try {
            ImageResponse imageResponse = imageService.saveImage(productId,file);
            return new ResponseEntity<>(new ResponseApi(imageResponse, "Upload success!!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Upload failed!!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
        try {
            DownloadImageResponse downloadImageResponse = imageService.getImageById(id);
            // for downloading the image
            // return ResponseEntity.ok()
            //         .contentType(MediaType.parseMediaType(downloadImageResponse.fileType()))
            //         .header(HttpHeaders.CONTENT_DISPOSITION,
            //                 "attachment; filename=\"" + downloadImageResponse.fileName() + "\"")
            //         .body(downloadImageResponse.image());
            
            // for view the image
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(downloadImageResponse.fileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + downloadImageResponse.fileName() + "\"")
                    .body(downloadImageResponse.image());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // No image, just null
        }
    }

    @PutMapping("")
    public ResponseEntity<ResponseApi> updateImage(@RequestBody UpdateImageRequest updateImageRequest) {
        try {
            return ResponseEntity
                    .ok(new ResponseApi(imageService.updateImage(updateImageRequest), "Updated success!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Update failed!"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteImage(@PathVariable Integer id) {
        try {
            imageService.deleteImageById(id);
            return ResponseEntity.ok(new ResponseApi(null,"Delete Success!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Delete failed!"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
