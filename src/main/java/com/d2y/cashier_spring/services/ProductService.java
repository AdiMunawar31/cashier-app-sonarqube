package com.d2y.cashier_spring.services;

import com.d2y.cashier_spring.dtos.ProductDTO;
import com.d2y.cashier_spring.exceptions.ResourceNotFoundException;
import com.d2y.cashier_spring.models.Product;
import com.d2y.cashier_spring.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductDetails(String rfId) {
        Product product = productRepository.findById(rfId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return mapToDTO(product);
    }

    private ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .rfId(product.getRfId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }
}
