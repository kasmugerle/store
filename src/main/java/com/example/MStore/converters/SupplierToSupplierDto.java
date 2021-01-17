package com.example.MStore.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.SupplierDto;
import com.example.MStore.model.Supplier;

@Component
public class SupplierToSupplierDto implements Converter<Supplier, SupplierDto> {

    private final ProductSupplyToProductSupplyDto productSupplyToProductSupplyDto;

    public SupplierToSupplierDto(ProductSupplyToProductSupplyDto productSupplyToProductSupplyDto) {
        this.productSupplyToProductSupplyDto = productSupplyToProductSupplyDto;
    }

    @Override
    public SupplierDto convert(Supplier supplier) {
        if(supplier == null) {
            return null;
        }
        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setName(supplier.getName());
        if(supplier.getProducts() != null && supplier.getProducts().size() > 0) {
            supplier.getProducts().forEach(productSupply -> {
                supplierDto.getProducts().add(productSupplyToProductSupplyDto.convert(productSupply));
            });
        }
        return supplierDto;
    }
}
