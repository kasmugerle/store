package com.example.MStore.services;

import java.util.List;

import com.example.MStore.dto.CategoryDto;
import com.example.MStore.dto.CategoryResponse;
import com.example.MStore.model.Category;

public interface CategoryService {

    List<CategoryResponse> getCategories();

    CategoryDto getCategoryProducts(Long categoryId);

    void createCategory(CategoryDto categoryDto);

    Category findCategory(Long categoryId);
}
