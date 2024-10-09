package com.d2y.cashier_spring.repositories;

import com.d2y.cashier_spring.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
