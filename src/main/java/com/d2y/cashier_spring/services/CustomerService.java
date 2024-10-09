package com.d2y.cashier_spring.services;

import com.d2y.cashier_spring.dtos.CustomerDTO;
import com.d2y.cashier_spring.exceptions.ResourceNotFoundException;
import com.d2y.cashier_spring.models.Customer;
import com.d2y.cashier_spring.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerDetails(String qrCode) {
        Customer customer = customerRepository.findById(qrCode)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return mapToDTO(customer);
    }

    private CustomerDTO mapToDTO(Customer customer) {
        return CustomerDTO.builder()
                .qrCode(customer.getQrCode())
                .name(customer.getName())
                .wallet(customer.getWallet())
                .build();
    }
}
