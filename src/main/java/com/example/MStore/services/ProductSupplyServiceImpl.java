package com.example.MStore.services;

import org.springframework.stereotype.Service;

import com.example.MStore.converters.ProductSupplyToProductSupplyDto;
import com.example.MStore.dto.ProductSupplyDto;
import com.example.MStore.exceptions.ResourceNotFoundException;
import com.example.MStore.model.Product;
import com.example.MStore.model.ProductSupply;
import com.example.MStore.model.Supplier;
import com.example.MStore.repositories.ProductRepository;
import com.example.MStore.repositories.ProductSupplyRepository;
import com.example.MStore.repositories.SupplierRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductSupplyServiceImpl implements ProductSupplyService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ProductSupplyRepository productSupplyRepository;
    private final ProductSupplyToProductSupplyDto productSupplyToProductSupplyDto;

    public ProductSupplyServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository, ProductSupplyRepository productSupplyRepository, ProductSupplyToProductSupplyDto productSupplyToProductSupplyDto) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.productSupplyRepository = productSupplyRepository;
        this.productSupplyToProductSupplyDto = productSupplyToProductSupplyDto;
    }

    @Override
    public List<ProductSupplyDto> getAllProductSupplies(Long productId) {
        Product product = this.findProduct(productId);
        if(product.getSuppliers().isEmpty()) {
            throw new RuntimeException("Product with id: " + productId + " has no supplies.");
        }
        return product.getSuppliers().stream().map(productSupplyToProductSupplyDto::convert).collect(Collectors.toList());
    }

    @Override
    public ProductSupplyDto addProductSupply(Long productId, Long supplierId, ProductSupplyDto productSupplyDto) {
        Product product = this.findProduct(productId);
        Supplier supplier = this.findSupplier(supplierId);
        ProductSupply productSupply = new ProductSupply(product, supplier);
        productSupply.setPrice(productSupplyDto.getPrice());
        productSupply.setDdv(productSupplyDto.getDdv());
        productSupply.setAmount(productSupplyDto.getAmount());
        ProductSupply productSupply1 = productSupplyRepository.save(productSupply);
        product.addSupplier(productSupply1);
        productRepository.save(product);
        supplier.addProduct(productSupply1);
        supplierRepository.save(supplier);
        ProductSupplyDto productSupplyDto1 = productSupplyToProductSupplyDto.convert(productSupply1);
        return productSupplyDto1;
    }

    @Override
    public ProductSupplyDto updateProductSupply(Long productId, Long supplierId, ProductSupplyDto productSupplyDto) {
        Product product = this.findProduct(productId);
        Supplier supplier = this.findSupplier(supplierId);
        ProductSupply productSupply = this.findProductSupply(supplier, product);
        supplier.removeProduct(productSupply);
        product.removeSupplier(productSupply);
        productSupply.setPrice(productSupplyDto.getPrice());
        productSupply.setDdv(productSupplyDto.getDdv());
        productSupply.setAmount(productSupply.getAmount() + productSupplyDto.getAmount());
        ProductSupply productSupply1 = productSupplyRepository.save(productSupply);
        product.addSupplier(productSupply1);
        productRepository.save(product);
        supplier.addProduct(productSupply1);
        supplierRepository.save(supplier);
        return productSupplyToProductSupplyDto.convert(productSupply1);
    }

    public Product findProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(!productOptional.isPresent()) {
            throw new ResourceNotFoundException("Product", "id", productId);
        }
        return productOptional.get();
    }

    public Supplier findSupplier(Long supplierId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        if(!supplierOptional.isPresent()) {
            throw new ResourceNotFoundException("Supplier", "id", supplierId);
        }
        return supplierOptional.get();
    }

    public ProductSupply findProductSupply(Supplier supplier, Product product) {
        return supplier.getProducts().stream()
                .filter(productSupply -> productSupply.getProduct().getId().equals(product.getId()))
                .filter(productSupply -> productSupply.getSupplier().getId().equals(supplier.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product supply with product id: " + product.getId() + " and supplier id: " + supplier.getId() + " not found."));
    }
}