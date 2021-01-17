package com.example.MStore.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.CartResponse;
import com.example.MStore.model.Cart;

@Component
public class CartToCartResponse implements Converter<Cart, CartResponse> {

    @Synchronized
    @Nullable
    @Override
    public CartResponse convert(Cart cart) {
        if(cart == null) {
            return null;
        }

        final CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId().toString());
        cartResponse.setCreatedAt(cart.getCreatedAt().toString());
        cartResponse.setPrice(Float.toString(cart.getPrice()));
        cartResponse.setUpdatedAt(cart.getUpdatedAt().toString());
        return cartResponse;
    }
}
