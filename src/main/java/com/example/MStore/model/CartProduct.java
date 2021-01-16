package com.example.MStore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_products")
public class CartProduct extends AuditModel {

    @EmbeddedId
    private CartProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cart_id")
    private Cart cart;

    private int amount;

    public CartProduct(Cart cart, Product product, int amount) {
        this.id = new CartProductId(cart.getId(),product.getId());
        this.cart = cart;
        this.product = product;
        this.amount = amount;
    }
}
