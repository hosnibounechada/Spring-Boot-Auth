package com.hb.auth.bootstrap;

import com.hb.auth.model.mongo.Product;
import com.hb.auth.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadMongoDB implements CommandLineRunner {
    private final ProductRepository productRepository;
    @Override
    public void run(String... args) {
        Product product = Product.builder().name("ASUS").description("Amazing laptop for gaming").build();
        productRepository.insert(product);
    }
}
