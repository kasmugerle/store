package com.example.MStore.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.MStore.dto.CartResponse;
import com.example.MStore.model.Message;
import com.example.MStore.security.CurrentUser;
import com.example.MStore.security.UserPrincipal;
import com.example.MStore.services.CartService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public List<CartResponse> getCartAllCarts(@CurrentUser UserPrincipal userPrincipal) {
        Long id = userPrincipal.getId();
        return cartService.getAllCarts(id);
    }

    @GetMapping("/recent")
    public CartResponse getRecentCart(@CurrentUser UserPrincipal userPrincipal) {
        Long id = userPrincipal.getId();
        return cartService.getRecentCart(id);
    }

    @PostMapping("")
    public CartResponse createCart(@CurrentUser UserPrincipal userPrincipal) {
        Long id = userPrincipal.getId();
        return cartService.createCart(id);
    }

    @DeleteMapping("/{cartId}")
    public Message deleteCart(@PathVariable Long cartId, @CurrentUser UserPrincipal userPrincipal) {
        Long id = userPrincipal.getId();
        return cartService.deleteCart(id, cartId);
    }
}
