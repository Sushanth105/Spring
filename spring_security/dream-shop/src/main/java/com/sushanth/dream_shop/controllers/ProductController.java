package com.sushanth.dream_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sushanth.dream_shop.dtos.product.request.AddProductRequest;
import com.sushanth.dream_shop.dtos.product.request.UpdateProductRequest;
import com.sushanth.dream_shop.response.ResponseApi;
import com.sushanth.dream_shop.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<ResponseApi> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return ResponseEntity.ok(new ResponseApi(productService.addProduct(addProductRequest), "Add success!!"));
    }

    @PutMapping("")
    public ResponseEntity<ResponseApi> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        return ResponseEntity
                .ok(new ResponseApi(productService.updateProduct(updateProductRequest), "Update success!!"));
    }

    @GetMapping("")
    public ResponseEntity<ResponseApi> getAllProduct() {
        return ResponseEntity.ok(new ResponseApi(productService.getAllProducts(), "Found!!"));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseApi> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseApi(productService.getProductById(id), "Found!!"));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseApi> getAllProductByName(@PathVariable String name) {
        return ResponseEntity.ok(new ResponseApi(productService.getAllProductsByName(name), "Found!!"));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<ResponseApi> getAllProductByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(new ResponseApi(productService.getAllProductsByBrand(brand), "Found!!"));
    }

    @GetMapping("/brandAndName")
    public ResponseEntity<ResponseApi> getAllProductByBrandAndName(@RequestParam String brand,
            @RequestParam String name) {
        return ResponseEntity
                .ok(new ResponseApi(productService.getAllProductsByBrandAndName(brand, name), "Found!!"));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseApi> getAllProductByCategory(@PathVariable String category) {
        return ResponseEntity.ok(new ResponseApi(productService.getAllProductsByCategory(category), "Found!!"));
    }

    @GetMapping("/categoryAndBrand")
    public ResponseEntity<ResponseApi> getAllProductByCategoryAndBrand(@RequestParam String category,
            @RequestParam String brand) {
        return ResponseEntity
                .ok(new ResponseApi(productService.getAllProductsByCategoryAndBrand(category, brand), "Found!!"));
    }

    @GetMapping("/count/brandAndName")
    public ResponseEntity<ResponseApi> countProductsByBrandAndName(@RequestParam String brand,
            @RequestParam String name) {
        return ResponseEntity
                .ok(new ResponseApi(productService.countProductsByBrandAndName(brand, name), "Found!!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok(new ResponseApi(null, "Delete success!!"));

    }

}
