package com.example.MStore.services;

import org.springframework.stereotype.Service;

import com.example.MStore.converters.CategoryToCategoryDto;
import com.example.MStore.converters.CategoryToCategoryResponse;
import com.example.MStore.dto.CategoryDto;
import com.example.MStore.dto.CategoryResponse;
import com.example.MStore.exceptions.ResourceNotFoundException;
import com.example.MStore.model.Category;
import com.example.MStore.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryDto categoryToCategoryDto;
    private final CategoryToCategoryResponse categoryToCategoryResponse;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryDto categoryToCategoryDto, CategoryToCategoryResponse categoryToCategoryResponse) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryDto = categoryToCategoryDto;
        this.categoryToCategoryResponse = categoryToCategoryResponse;
    }


    @Override
    public List<CategoryResponse> getCategories() {
        if(categoryRepository.findAll().isEmpty()) {
            throw new RuntimeException("There are no categories");
        }
        return categoryRepository.findAll().stream().map(categoryToCategoryResponse::convert).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryProducts(Long categoryId) {
        Category category = this.findCategory(categoryId);
        return categoryToCategoryDto.convert(category);
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public Category findCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(!categoryOptional.isPresent()) {
            throw new ResourceNotFoundException("category", "id", categoryId);
        }
        return categoryOptional.get();
    }
}
