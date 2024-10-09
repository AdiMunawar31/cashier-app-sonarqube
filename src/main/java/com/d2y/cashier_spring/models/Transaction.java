package com.d2y.cashier_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "qr_code", referencedColumnName = "qrCode", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rf_id", referencedColumnName = "rfId", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int price;

    @Column
    private int totalPrice;

    @Column
    private LocalDateTime date;

}
