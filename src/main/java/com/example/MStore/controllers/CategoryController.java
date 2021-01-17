package com.example.MStore.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.MStore.dto.CategoryDto;
import com.example.MStore.dto.CategoryResponse;
import com.example.MStore.services.CategoryService;

import java.util.List;
import java.util.Set;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public List<CategoryResponse> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/api/categories/{categoryId}")
    public CategoryDto getCategoryProducts(@PathVariable Long categoryId) {
        return categoryService.getCategoryProducts(categoryId);
    }

    @PostMapping("/admin/api/categories")
    public void createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
    }
}
