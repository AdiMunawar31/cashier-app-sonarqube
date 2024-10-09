package com.d2y.cashier_spring.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String qrCode;
    private String rfId;
    private int price;
    private int totalPrice;
    private LocalDateTime date;
}
