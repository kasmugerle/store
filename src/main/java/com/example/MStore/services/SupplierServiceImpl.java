package com.example.MStore.services;

import org.springframework.stereotype.Service;

import com.example.MStore.converters.SupplierToSupplierDto;
import com.example.MStore.dto.SupplierDto;
import com.example.MStore.model.Supplier;
import com.example.MStore.repositories.SupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierToSupplierDto supplierToSupplierDto;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierToSupplierDto supplierToSupplierDto) {
        this.supplierRepository = supplierRepository;
        this.supplierToSupplierDto = supplierToSupplierDto;
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        if(supplierRepository.findAll().isEmpty()) {
            throw new RuntimeException("There are no suppliers.");
        }
        return supplierRepository.findAll().stream().map(supplierToSupplierDto::convert).collect(Collectors.toList());
    }

    @Override
    public void createSupplier(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplierRepository.save(supplier);
    }
}
