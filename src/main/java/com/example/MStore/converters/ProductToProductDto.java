package com.example.MStore.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.ProductDto;
import com.example.MStore.model.Product;

@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Synchronized
    @Nullable
    @Override
    public ProductDto convert(Product product) {
        if(product == null) {
            return null;
        }
        final ProductDto productDto = new ProductDto();
        productDto.setId(product.getId().toString());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDdv(product.getDdv());
        return productDto;
    }
}
