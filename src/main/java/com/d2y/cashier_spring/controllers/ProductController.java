package com.d2y.cashier_spring.controllers;

import com.d2y.cashier_spring.dtos.ProductDTO;
import com.d2y.cashier_spring.models.Product;
import com.d2y.cashier_spring.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{rfId}")
    public ResponseEntity<ProductDTO> getProductDetails(@PathVariable String rfId) {
        return ResponseEntity.ok(productService.getProductDetails(rfId));
    }
}

