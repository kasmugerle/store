package com.example.MStore.services;

import java.util.List;

import com.example.MStore.dto.ProductSupplyDto;

public interface ProductSupplyService {

    List<ProductSupplyDto> getAllProductSupplies(Long productId);

    ProductSupplyDto addProductSupply(Long productId, Long supplierId, ProductSupplyDto productSupplyDto);

    ProductSupplyDto updateProductSupply(Long productId, Long supplierId, ProductSupplyDto productSupplyDto);
}
