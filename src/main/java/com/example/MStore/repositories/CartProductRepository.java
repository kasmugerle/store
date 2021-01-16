package com.example.MStore.repositories;

import com.example.store.model.CartProduct;
import com.example.store.model.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {
}
