package com.example.MStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MStore.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
