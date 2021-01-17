package com.example.MStore.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.ProductSupplyDto;
import com.example.MStore.model.ProductSupply;

@Component
public class ProductSupplyToProductSupplyDto implements Converter<ProductSupply, ProductSupplyDto> {

    @Synchronized
    @Nullable
    @Override
    public ProductSupplyDto convert(ProductSupply productSupply) {
        if(productSupply == null) {
            return null;
        }
        final ProductSupplyDto productSupplyDto = new ProductSupplyDto();
        productSupplyDto.setName(productSupply.getProduct().getName());
        productSupplyDto.setPrice(productSupply.getPrice());
        productSupplyDto.setAmount(productSupply.getAmount());
        productSupplyDto.setDdv(productSupply.getDdv());
        productSupplyDto.setCreatedAt(productSupply.getCreatedAt().toString());
        return productSupplyDto;
    }
}
