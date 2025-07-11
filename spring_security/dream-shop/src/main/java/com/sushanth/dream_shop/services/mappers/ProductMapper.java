package com.sushanth.dream_shop.services.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushanth.dream_shop.dtos.category.response.CategoryResponse;
import com.sushanth.dream_shop.dtos.image.response.ImageResponse;
import com.sushanth.dream_shop.dtos.product.request.AddProductRequest;
import com.sushanth.dream_shop.dtos.product.request.UpdateProductRequest;
import com.sushanth.dream_shop.dtos.product.response.ProductResponse;
import com.sushanth.dream_shop.models.Category;
import com.sushanth.dream_shop.models.Product;

@Service
public class ProductMapper {

    @Autowired
    private ImageMapper imageMapper;

    public Product addProductRequestToProduct(AddProductRequest addProductRequest, Category category) {
        return new Product(addProductRequest.name(), addProductRequest.brand(), addProductRequest.price(),
                addProductRequest.inventory(), addProductRequest.description(), category);
    }

    public Product updateProductRequestToProduct(UpdateProductRequest updateProductRequest, Category category,
            Product existingProduct) {
        existingProduct.setId(updateProductRequest.id());
        existingProduct.setName(updateProductRequest.name());
        existingProduct.setBrand(updateProductRequest.brand());
        existingProduct.setPrice(updateProductRequest.price());
        existingProduct.setDescription(updateProductRequest.description());
        existingProduct.setInventory(updateProductRequest.inventory());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    public ProductResponse productToProductResponse(Product product) {
        List<ImageResponse> imageResponses = product.getImages().stream().map(imageMapper::imageToImageResponse)
                .toList();
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getInventory(),
                product.getDescription(),
                new CategoryResponse(product.getCategory().getId(), product.getCategory().getName()),
                imageResponses);
    }
}
