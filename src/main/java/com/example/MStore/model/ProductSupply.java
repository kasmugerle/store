package com.example.MStore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_supply")
public class ProductSupply extends AuditModel {

    @EmbeddedId
    private ProductSupplyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("supplier_id")
    private Supplier supplier;

    private float price;

    private float ddv;

    private int amount;

    public ProductSupply(Product product, Supplier supplier) {
        this.id = new ProductSupplyId(product.getId(), supplier.getId());
        this.product = product;
        this.supplier = supplier;
    }
}
