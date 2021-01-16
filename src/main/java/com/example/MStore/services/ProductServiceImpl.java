package com.example.MStore.services;

import org.springframework.stereotype.Service;

import com.example.MStore.converters.ProductToProductDto;
import com.example.MStore.dto.ProductDto;
import com.example.MStore.exceptions.ResourceNotFoundException;
import com.example.MStore.model.Category;
import com.example.MStore.model.Product;
import com.example.MStore.repositories.CategoryRepository;
import com.example.MStore.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductToProductDto productToProductDto;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductToProductDto productToProductDto, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productToProductDto = productToProductDto;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        if(productRepository.findAll().isEmpty()) {
            throw  new RuntimeException("There are no products in the store.");
        }
        return productRepository.findAll().stream().map(productToProductDto::convert).collect(Collectors.toList());
    }

    @Override
    public ProductDto addProduct(Long categoryId, ProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(!categoryOptional.isPresent()) {
            throw new ResourceNotFoundException("category", "id", categoryId);
        }
        Category category = categoryOptional.get();
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDdv(productDto.getDdv());
        product.setCategory(category);
        Product product1 = productRepository.save(product);
        category.addProduct(product1);
        categoryRepository.save(category);
        return productToProductDto.convert(product1);
    }
}
