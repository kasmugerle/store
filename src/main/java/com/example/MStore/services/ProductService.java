package com.example.MStore.services;

import com.example.store.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto addProduct(Long categoryId, ProductDto productDto);
}
