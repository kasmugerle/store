package com.example.MStore.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.CartProductDto;
import com.example.MStore.model.CartProduct;

@Component
public class CartProductToCartProductDto implements Converter<CartProduct, CartProductDto> {

    @Synchronized
    @Nullable
    @Override
    public CartProductDto convert(CartProduct cartProduct) {
        if(cartProduct == null) {
            return null;
        }
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setProductId(cartProduct.getProduct().getId().toString());
        cartProductDto.setName(cartProduct.getProduct().getName());
        cartProductDto.setPrice(cartProduct.getProduct().getPrice());
        cartProductDto.setAmount(cartProduct.getAmount());
        return cartProductDto;
    }
}
