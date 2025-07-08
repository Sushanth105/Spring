package com.sushanth.dream_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            return ResponseEntity.ok(new ResponseApi(productService.addProduct(addProductRequest), "Add success!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Add failed!!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<ResponseApi> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        try {
            return ResponseEntity
                    .ok(new ResponseApi(productService.updateProduct(updateProductRequest), "Update success!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Update failed!!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseApi> getAllProduct() {
        try {
            return ResponseEntity.ok(new ResponseApi(productService.getAllProducts(), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseApi> getProductById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(new ResponseApi(productService.getProductById(id), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseApi> getAllProductByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(new ResponseApi(productService.getAllProductsByName(name), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<ResponseApi> getAllProductByBrand(@PathVariable String brand) {
        try {
            return ResponseEntity.ok(new ResponseApi(productService.getAllProductsByBrand(brand), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/brandAndName")
    public ResponseEntity<ResponseApi> getAllProductByBrandAndName(@RequestParam String brand,
            @RequestParam String name) {
        try {
            return ResponseEntity
                    .ok(new ResponseApi(productService.getAllProductsByBrandAndName(brand, name), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseApi> getAllProductByCategory(@PathVariable String category) {
        try {
            return ResponseEntity.ok(new ResponseApi(productService.getAllProductsByCategory(category), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categoryAndBrand")
    public ResponseEntity<ResponseApi> getAllProductByCategoryAndBrand(@RequestParam String category,
            @RequestParam String brand) {
        try {
            return ResponseEntity
                    .ok(new ResponseApi(productService.getAllProductsByCategoryAndBrand(category, brand), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count/brandAndName")
    public ResponseEntity<ResponseApi> countProductsByBrandAndName(@RequestParam String brand,
            @RequestParam String name) {
        try {
            return ResponseEntity
                    .ok(new ResponseApi(productService.countProductsByBrandAndName(brand, name), "Found!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Not Found!!"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteProductById(@PathVariable Integer id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ResponseApi(null, "Delete success!!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi(e.getMessage(), "Delete failed!!"), HttpStatus.NOT_FOUND);
        }

    }

}
