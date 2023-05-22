package com.hb.auth.repository;

import com.hb.auth.model.mongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
