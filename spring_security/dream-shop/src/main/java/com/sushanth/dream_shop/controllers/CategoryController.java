package com.sushanth.dream_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushanth.dream_shop.dtos.category.request.AddCategoryRequest;
import com.sushanth.dream_shop.dtos.category.request.UpdateCategoryRequest;
import com.sushanth.dream_shop.response.ResponseApi;
import com.sushanth.dream_shop.services.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ResponseApi> getAllCategory() {
        return ResponseEntity.ok(new ResponseApi(categoryService.getAllCategories(), "Found!!"));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseApi> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseApi(categoryService.getCategoryById(id), "Found!!"));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseApi> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(new ResponseApi(categoryService.getCategoryByName(name), "Found!!"));
    }

    @PostMapping("")
    public ResponseEntity<ResponseApi> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        return ResponseEntity.ok(new ResponseApi(categoryService.addCategory(addCategoryRequest), "Add Success!!"));
    }

    @PutMapping("")
    public ResponseEntity<ResponseApi> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity
                .ok(new ResponseApi(categoryService.updateCategory(updateCategoryRequest), "Update Success!!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(new ResponseApi(null, "Delete Success!!"));
    }
}
