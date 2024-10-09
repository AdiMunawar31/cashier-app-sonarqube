package com.d2y.cashier_spring.controllers;

import com.d2y.cashier_spring.dtos.CustomerDTO;
import com.d2y.cashier_spring.models.Customer;
import com.d2y.cashier_spring.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{qrCode}")
    public ResponseEntity<CustomerDTO> getCustomerDetails(@PathVariable String qrCode) {
        return ResponseEntity.ok(customerService.getCustomerDetails(qrCode));
    }
}

