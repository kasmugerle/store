package com.example.MStore.repositories;

import com.example.store.model.ProductSupply;
import com.example.store.model.ProductSupplyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSupplyRepository extends JpaRepository<ProductSupply, ProductSupplyId> {
}
