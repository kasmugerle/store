package com.example.MStore.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.MStore.dto.SupplierDto;
import com.example.MStore.services.SupplierService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public List<SupplierDto> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PostMapping("")
    public void createSupplier(@RequestBody SupplierDto supplierDto) {
        supplierService.createSupplier(supplierDto);
    }
}
