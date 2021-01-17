package com.example.MStore.services;

import org.springframework.stereotype.Service;

import com.example.MStore.converters.CartProductToCartProductDto;
import com.example.MStore.dto.CartProductDto;
import com.example.MStore.exceptions.AppException;
import com.example.MStore.exceptions.ResourceNotFoundException;
import com.example.MStore.model.Cart;
import com.example.MStore.model.CartProduct;
import com.example.MStore.model.Message;
import com.example.MStore.model.Product;
import com.example.MStore.repositories.CartProductRepository;
import com.example.MStore.repositories.CartRepository;
import com.example.MStore.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartProductServiceImpl implements CartProductService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final CartProductToCartProductDto cartProductToCartProductDto;

    public CartProductServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository, ProductRepository productRepository, CartProductToCartProductDto cartProductToCartProductDto) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.productRepository = productRepository;
        this.cartProductToCartProductDto = cartProductToCartProductDto;
    }

    @Override
    public List<CartProductDto> getCartProducts(Long cartId) {
        Cart cart = this.findCart(cartId);
        return cart.getProducts().stream().map(cartProductToCartProductDto::convert).collect(Collectors.toList());
    }

    @Override
    public CartProductDto addCartProduct(Long cartId, Long productId, CartProductDto cartProductDto) {
        Cart cart = this.findCart(cartId);
        Product product = this.findProduct(productId);
        if(cartProductDto.getAmount() >= product.getAmount()) {
            throw new AppException("Sorry we do not have that many products at the moment. " + product.getAmount() + " left.");
        }
        CartProduct cartProduct = cartProductRepository.save(new CartProduct(cart, product, cartProductDto.getAmount()));
        product.addCart(cartProduct);
        productRepository.save(product);
        cart.addProduct(cartProduct);
        cartRepository.save(cart);
        CartProductDto cartProductDto1 = cartProductToCartProductDto.convert(cartProduct);
        cartProductDto1.setMessage("Added " + cartProductDto1.getAmount() + " " + product.getName() + " to your cart.");
        return cartProductDto1;
    }

    @Override
    public CartProductDto updateCartProduct(Long cartId, Long productId, CartProductDto cartProductDto) {
        Cart cart = this.findCart(cartId);
        Product product = this.findProduct(productId);
        CartProduct cartProduct = this.findCartProduct(cart, product);
        int amount = cartProduct.getAmount();
        product.removeCart(cartProduct);
        cart.removeProduct(cartProduct);
        cartProduct.setAmount(cartProductDto.getAmount());
        CartProduct cartProduct1 = cartProductRepository.save(cartProduct);
        product.addCart(cartProduct1);
        productRepository.save(product);
        cart.addProduct(cartProduct1);
        cartRepository.save(cart);
        CartProductDto cartProductDto1 = cartProductToCartProductDto.convert(cartProduct1);
        cartProductDto1.setMessage("You now have " + cartProductDto1.getAmount() + " " + product.getName() + " in your cart.");
        return cartProductDto1;
    }

    @Override
    public Message deleteCartProduct(Long cartId, Long productId) {
        Cart cart = this.findCart(cartId);
        Product product = this.findProduct(productId);
        CartProduct cartProduct = this.findCartProduct(cart, product);
        cart.removeProduct(cartProduct);
        cartRepository.save(cart);
        product.removeCart(cartProduct);
        productRepository.save(product);
        cartProductRepository.delete(cartProduct);
        return new Message("Product " + product.getName() + " deleted from your cart.");
    }

    public Cart findCart(Long cartId) {
        Optional<Cart> cart1 = cartRepository.findById(cartId);
        if(!cart1.isPresent()) {
            throw new ResourceNotFoundException("Cart", "id", cartId);
        }
        return cart1.get();
    }

    public Product findProduct(Long productId) {
        Optional<Product> product1 = productRepository.findById(productId);
        if(!product1.isPresent()) {
            throw new ResourceNotFoundException("Product", "id", productId);
        }
        return product1.get();
    }

    public CartProduct findCartProduct(Cart cart, Product product) {
        return cart.getProducts()
                .stream()
                .filter(cartProduct -> cartProduct.getCart().getId().equals(cart.getId()))
                .filter(cartProduct -> cartProduct.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart product with cart id: " + cart.getId() + " and product id: " + product.getId() + " not found!"));
    }
}
