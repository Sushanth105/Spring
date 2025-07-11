package com.sushanth.dream_shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushanth.dream_shop.Repositories.CategoryRepository;
import com.sushanth.dream_shop.Repositories.ProductRepository;
import com.sushanth.dream_shop.dtos.product.request.AddProductRequest;
import com.sushanth.dream_shop.dtos.product.request.UpdateProductRequest;
import com.sushanth.dream_shop.dtos.product.response.ProductResponse;
import com.sushanth.dream_shop.exceptions.ProductNotFoundException;
import com.sushanth.dream_shop.models.Category;
import com.sushanth.dream_shop.models.Product;
import com.sushanth.dream_shop.services.mappers.ProductMapper;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public ProductResponse addProduct(AddProductRequest addProductRequest) {
        // checking if category present if not present add it
        // here orElse(...) — eager evaluation and orElseGet(...) — lazy evaluation
        Category category = categoryRepository.findByName(addProductRequest.categoryName())
                .orElseGet(() -> categoryRepository.save(new Category(addProductRequest.categoryName())));

        Product product = productMapper.addProductRequestToProduct(addProductRequest, category);

        return productMapper.productToProductResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
        Category category = categoryRepository.findByName(updateProductRequest.categoryName())
                .orElseGet(() -> categoryRepository.save(new Category(updateProductRequest.categoryName())));

        Product product = productRepository.findById(updateProductRequest.id())
                .map(eProduct -> productMapper.updateProductRequestToProduct(updateProductRequest, category,
                        eProduct))
                .map(productRepository::save).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.productToProductResponse(product);
    }

    @Transactional
    public ProductResponse getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        return productMapper.productToProductResponse(product);
    }

    @Transactional
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll().stream().map(productMapper::productToProductResponse).toList();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No Product found");
        }

        return products;
    }

    @Transactional
    public List<ProductResponse> getAllProductsByCategory(String categoryName) {
        var products = productRepository.findByCategoryName(categoryName).stream()
                .map(productMapper::productToProductResponse)
                .toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product with category name " + categoryName + " not found");
        }

        return products;
    }

    @Transactional
    public List<ProductResponse> getAllProductsByBrand(String brand) {
        var products = productRepository.findByBrand(brand).stream().map(productMapper::productToProductResponse)
                .toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product with brand " + brand + " not found");
        }

        return products;

    }

    @Transactional
    public List<ProductResponse> getAllProductsByCategoryAndBrand(String categoryName, String brand) {
        var products = productRepository.findByCategoryNameAndBrand(categoryName, brand).stream()
                .map(productMapper::productToProductResponse).toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "Product with category name " + categoryName + " and brand " + brand + " not found");
        }

        return products;
    }

    @Transactional
    public List<ProductResponse> getAllProductsByName(String name) {
        var products =  productRepository.findByName(name).stream().map(productMapper::productToProductResponse).toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "Product with name " + name + " not found");
        }

        return products;
    }

    @Transactional
    public List<ProductResponse> getAllProductsByBrandAndName(String brand, String name) {
        var products = productRepository.findByBrandAndName(brand, name).stream()
                .map(productMapper::productToProductResponse)
                .toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "Product with brand " + brand + " and name " + name + " not found");
        }

        return products;
    }

    public Integer countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    public void deleteProductById(Integer id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                () -> {
                    throw new ProductNotFoundException("Product with id " + id + " not found");
                });
        ;
    }

}
