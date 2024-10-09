package com.d2y.cashier_spring.services;

import com.d2y.cashier_spring.dtos.TransactionDTO;
import com.d2y.cashier_spring.models.Transaction;
import com.d2y.cashier_spring.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO createTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDTO(savedTransaction);
    }

    private TransactionDTO mapToDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .qrCode(transaction.getCustomer().getQrCode())
                .rfId(transaction.getProduct().getRfId())
                .price(transaction.getPrice())
                .totalPrice(transaction.getTotalPrice())
                .date(transaction.getDate())
                .build();
    }
}
