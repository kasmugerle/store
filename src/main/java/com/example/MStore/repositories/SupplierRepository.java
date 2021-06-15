package com.example.MStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MStore.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
