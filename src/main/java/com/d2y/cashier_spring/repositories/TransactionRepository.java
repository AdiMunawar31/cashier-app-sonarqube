package com.d2y.cashier_spring.repositories;

import com.d2y.cashier_spring.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
