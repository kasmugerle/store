package com.example.MStore.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.MStore.dto.ProductDto;
import com.example.MStore.services.ProductService;

import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/admin/api/category/{categoryId}/products")
    public ProductDto addProduct(@PathVariable Long categoryId, @RequestBody ProductDto productDto) {
        return productService.addProduct(categoryId, productDto);
    }
}
