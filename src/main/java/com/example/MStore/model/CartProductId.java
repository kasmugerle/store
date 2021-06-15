package com.example.MStore.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CartProductId implements Serializable {

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;
}
