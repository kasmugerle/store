package com.example.MStore.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.CategoryDto;
import com.example.MStore.model.Category;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDto> {

    private final ProductToProductDto productToProductDto;

    public CategoryToCategoryDto(ProductToProductDto productToProductDto) {
        this.productToProductDto = productToProductDto;
    }

    @Override
    public CategoryDto convert(Category category) {
        if(category == null) {
            return null;
        }
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId().toString());
        categoryDto.setName(category.getName());
        if(category.getProducts() != null && category.getProducts().size() > 0) {
            category.getProducts().forEach(product -> {
                categoryDto.getProducts().add(productToProductDto.convert(product));
            });
        }
        return categoryDto;
    }
}
