package com.example.MStore.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.MStore.dto.CartProductDto;
import com.example.MStore.model.Message;
import com.example.MStore.services.CartProductService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/carts")
public class CartProductController {

    private final CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @GetMapping("/{cartId}")
    public List<CartProductDto> getAllCartProducts(@PathVariable Long cartId) {
        return cartProductService.getCartProducts(cartId);
    }

    @PostMapping("/{cartId}/products/{productId}")
    public CartProductDto addCartProduct(@PathVariable Long cartId, @PathVariable Long productId, @RequestBody CartProductDto cartProductDto) {
        return cartProductService.addCartProduct(cartId, productId, cartProductDto);
    }

    @PutMapping("/{cartId}/products/{productId}")
    public CartProductDto updateCartProduct(@PathVariable Long cartId, @PathVariable Long productId, @RequestBody CartProductDto cartProductDto) {
        return cartProductService.updateCartProduct(cartId, productId, cartProductDto);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public Message deleteCartProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        return cartProductService.deleteCartProduct(cartId, productId);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String name = e.getName();
        String type = e.getRequiredType().getSimpleName();
        Object value = e.getValue();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't",
                name, type, value);
        System.out.println(message);
    }

}
