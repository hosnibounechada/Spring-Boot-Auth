package com.hb.auth.service;

import com.hb.auth.exception.NotFoundException;
import com.hb.auth.model.mongo.Product;
import com.hb.auth.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product findById(String id){
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public void delete(String id){
        productRepository.deleteById(id);
    }
}
