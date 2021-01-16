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
public class ProductSupplyId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "supplier_id")
    private Long supplierId;
}
