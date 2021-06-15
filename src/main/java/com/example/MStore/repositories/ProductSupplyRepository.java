package com.example.MStore.repositories;

import com.example.MStore.model.ProductSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSupplyRepository extends JpaRepository<ProductSupply, ProductSupply> {
}
