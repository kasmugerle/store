package com.example.MStore.services;

import java.util.List;

import com.example.MStore.dto.CartResponse;
import com.example.MStore.model.Message;

public interface CartService {

    List<CartResponse> getAllCarts(Long id);

    CartResponse getRecentCart(Long id);

    CartResponse createCart(Long id);

    Message deleteCart(Long id, Long cartId);
}
