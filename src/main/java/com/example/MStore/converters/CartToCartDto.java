package com.example.MStore.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.MStore.dto.CartDto;
import com.example.MStore.model.Cart;

@Component
public class CartToCartDto implements Converter<Cart, CartDto> {

    private final CartProductToCartProductDto cartProductToCartProductDto;

    public CartToCartDto(CartProductToCartProductDto cartProductToCartProductDto) {
        this.cartProductToCartProductDto = cartProductToCartProductDto;
    }

    @Synchronized
    @Nullable
    @Override
    public CartDto convert(Cart cart) {
        if(cart == null) {
            return null;
        }

        final CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId().toString());
        cartDto.setPrice(cart.getPrice());
        if(cart.getProducts() != null && cart.getProducts().size() > 0) {
            cart.getProducts().forEach(cartProduct -> {
                cartDto.getCartProducts().add(cartProductToCartProductDto.convert(cartProduct));
            });
        }
        return cartDto;
    }
}
