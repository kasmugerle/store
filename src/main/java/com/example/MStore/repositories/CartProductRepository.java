package com.example.MStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MStore.model.CartProduct;
import com.example.MStore.model.CartProductId;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {
}
