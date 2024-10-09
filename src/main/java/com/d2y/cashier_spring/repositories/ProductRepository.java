package com.d2y.cashier_spring.repositories;

import com.d2y.cashier_spring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

