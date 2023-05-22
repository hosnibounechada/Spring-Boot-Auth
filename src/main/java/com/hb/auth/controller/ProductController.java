package com.hb.auth.controller;

import com.hb.auth.model.mongo.Product;
import com.hb.auth.payload.request.product.CreateProductRequest;
import com.hb.auth.payload.request.product.UpdateProductRequest;
import com.hb.auth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/products")
@Validated
@EnableMethodSecurity
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody CreateProductRequest request) {
        Product product = Product.builder().name(request.name()).description(request.description()).build();

        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody UpdateProductRequest request) {
        Product product = Product.builder().id(request.id()).name(request.name()).description(request.description()).build();

        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
