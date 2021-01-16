package com.example.MStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<CartProduct> products = new HashSet<>();

    private float price;

    public void addProduct(CartProduct cartProduct) {
        this.getProducts().add(cartProduct);
        float p = cartProduct.getProduct().getPrice() * cartProduct.getAmount();
        this.setPrice(this.price + p);
    }

    public void removeProduct(CartProduct cartProduct) {
        float p = cartProduct.getProduct().getPrice() * cartProduct.getAmount();
        this.setPrice(this.price - p);
        this.getProducts().remove(cartProduct);
    }

    public void setUpdatedDate() {
        this.setUpdatedAt(OffsetDateTime.now());
    }
}
