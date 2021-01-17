package com.example.MStore.services;

import java.util.List;

import com.example.MStore.dto.CartProductDto;
import com.example.MStore.model.Message;

public interface CartProductService {

    List<CartProductDto> getCartProducts(Long cartId);

    CartProductDto addCartProduct(Long cartId, Long productId, CartProductDto cartProductDto);

    CartProductDto updateCartProduct(Long cartId, Long productId, CartProductDto cartProductDto);

    Message deleteCartProduct(Long cartId, Long productId);
}
