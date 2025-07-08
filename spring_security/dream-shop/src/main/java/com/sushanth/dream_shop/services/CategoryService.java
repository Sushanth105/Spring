package com.sushanth.dream_shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushanth.dream_shop.Repositories.CategoryRepository;
import com.sushanth.dream_shop.dtos.category.request.AddCategoryRequest;
import com.sushanth.dream_shop.dtos.category.request.UpdateCategoryRequest;
import com.sushanth.dream_shop.dtos.category.response.CategoryResponse;
import com.sushanth.dream_shop.exceptions.CategoryAlreadyExistsException;
import com.sushanth.dream_shop.exceptions.CategoryNotFoundException;
import com.sushanth.dream_shop.models.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
        
        return new CategoryResponse(category.getId(), category.getName());
    }

    public CategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " not found"));
        return new CategoryResponse(category.getId(), category.getName());
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map((c)-> new CategoryResponse(c.getId(),c.getName())).toList();
    }

    public CategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        categoryRepository.findByName(addCategoryRequest.name()).ifPresent( c -> {
            throw new CategoryAlreadyExistsException(
                    "The Category with name " + addCategoryRequest.name() + " already exist");
        });
        Category category = categoryRepository.save(new Category(addCategoryRequest.name()));
        return new CategoryResponse(category.getId(), category.getName());
    }

    public CategoryResponse updateCategory(UpdateCategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryRequest.id()).map((existingCategory) -> {
            existingCategory.setName(categoryRequest.name());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return new CategoryResponse(category.getId(), category.getName());
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                () -> {
                    throw new CategoryNotFoundException("Category with id " + id + " not found");
                });
    }
}
