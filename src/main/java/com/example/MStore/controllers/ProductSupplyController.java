package com.example.MStore.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.MStore.dto.ProductSupplyDto;
import com.example.MStore.services.ProductSupplyService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/api/products")
public class ProductSupplyController {

    private final ProductSupplyService productSupplyService;

    public ProductSupplyController(ProductSupplyService productSupplyService) {
        this.productSupplyService = productSupplyService;
    }

    @GetMapping("/{productId}")
    public List<ProductSupplyDto> getAllProductSupplies(@PathVariable Long productId) {
        return productSupplyService.getAllProductSupplies(productId);
    }

    @PostMapping("/{productId}/suppliers/{supplierId}")
    public ProductSupplyDto addProductSupply(@PathVariable Long productId, @PathVariable Long supplierId, @RequestBody ProductSupplyDto productSupplyDto) {
        return productSupplyService.addProductSupply(productId, supplierId, productSupplyDto);
    }

    @PutMapping("/{productId}/suppliers/{supplierId}")
    public ProductSupplyDto updateProductSupply(@PathVariable Long productId, @PathVariable Long supplierId, @RequestBody ProductSupplyDto productSupplyDto) {
        return productSupplyService.updateProductSupply(productId, supplierId, productSupplyDto);
    }
}
