package com.example.MStore.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.CategoryResponse;
import com.example.MStore.model.Category;

@Component
public class CategoryToCategoryResponse implements Converter<Category, CategoryResponse> {

    @Override
    public CategoryResponse convert(Category category) {
        if(category == null) {
            return null;
        }
        final CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId().toString());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
}
