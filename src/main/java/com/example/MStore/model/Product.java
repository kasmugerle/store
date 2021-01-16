package com.example.MStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float price;

    private float ddv;

    private int amount;

    @JsonIgnore
    @OneToMany(mappedBy = "product" , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<CartProduct> carts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductSupply> suppliers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public void addCart(CartProduct cartProduct) {
        this.getCarts().add(cartProduct);
        this.setAmount(this.getAmount() - cartProduct.getAmount());
    }

    public void removeCart(CartProduct cartProduct) {
        int a = cartProduct.getAmount();
        this.setAmount(this.getAmount() + a);
        this.getCarts().remove(cartProduct);
    }

    public void addSupplier(ProductSupply productSupply) {
        this.getSuppliers().add(productSupply);
        int a = productSupply.getAmount();
        this.setAmount(this.getAmount() + a);
    }

    public void removeSupplier(ProductSupply productSupply) {
        this.getSuppliers().remove(productSupply);
    }
}
