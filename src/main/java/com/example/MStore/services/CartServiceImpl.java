package com.example.MStore.services;

import org.springframework.stereotype.Service;

import com.example.MStore.converters.CartToCartDto;
import com.example.MStore.converters.CartToCartResponse;
import com.example.MStore.dto.CartResponse;
import com.example.MStore.exceptions.ResourceNotFoundException;
import com.example.MStore.model.Cart;
import com.example.MStore.model.Message;
import com.example.MStore.model.User;
import com.example.MStore.repositories.CartProductRepository;
import com.example.MStore.repositories.CartRepository;
import com.example.MStore.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartToCartDto cartToCartDto;
    private final CartToCartResponse cartToCartResponse;
    private final CartProductRepository cartProductRepository;

    public CartServiceImpl(UserRepository userRepository, CartRepository cartRepository, CartToCartDto cartToCartDto, CartToCartResponse cartToCartResponse, CartProductRepository cartProductRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartToCartDto = cartToCartDto;
        this.cartToCartResponse = cartToCartResponse;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public List<CartResponse> getAllCarts(Long id) {
        User user = this.findUser(id);
        List<Cart> carts = user.getCartsList();
        return carts.stream().map(cartToCartResponse::convert).collect(Collectors.toList());
    }

    @Override
    public CartResponse getRecentCart(Long id) {
        User user = this.findUser(id);
        CartResponse cartResponse;
        if(user.getCarts() == null || user.getCarts().size() < 1) {
            cartResponse = this.createCart(id);
            cartResponse.setMessage("You had no carts, so you got one automatically");
        }
        else {
            Cart cart = Collections.max(cartRepository.findAll(), Comparator.comparing(Cart::getCreatedAt));
            cart.setUpdatedDate();
            cartResponse = cartToCartResponse.convert(cart);
        }
        return cartResponse;
    }

    @Override
    public CartResponse createCart(Long id) {
        User user = this.findUser(id);
        Cart cart = new Cart();
        cart.setPrice(0);
        user.addCart(cart);
        Cart cart1 = cartRepository.save(cart);
        CartResponse cartResponse = cartToCartResponse.convert(cart1);
        assert cartResponse != null;
        cartResponse.setMessage("Created cart");
        return cartResponse;
    }

    @Override
    public Message deleteCart(Long id, Long cartId) {
        User user = this.findUser(id);
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(!cartOptional.isPresent()) {
            throw new ResourceNotFoundException("Cart", "id", cartId);
        }
        Cart cart = cartOptional.get();
        if(cart.getProducts().size() > 0) {
            cartProductRepository.deleteAll(cart.getProducts());
            cart.getProducts().clear();
        }
        cartRepository.delete(cart);
        user.getCarts().remove(cart);
        return new Message("Cart " + cart.getId() + " deleted Successfully");
    }

    public User findUser(Long id) {
        Optional<User> user1 = userRepository.findById(id);
        if(!user1.isPresent()) {
            throw new ResourceNotFoundException("User", "Id", id);
        }
        return user1.get();
    }
}
