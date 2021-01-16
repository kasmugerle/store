package com.example.MStore.services;

import java.util.List;

import com.example.MStore.dto.ProductDto;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto addProduct(Long categoryId, ProductDto productDto);
}
