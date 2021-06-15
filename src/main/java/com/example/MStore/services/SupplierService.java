package com.example.MStore.services;

import java.util.List;

import com.example.MStore.dto.SupplierDto;

public interface SupplierService {

    List<SupplierDto> getAllSuppliers();

    void createSupplier(SupplierDto supplierDto);
}
