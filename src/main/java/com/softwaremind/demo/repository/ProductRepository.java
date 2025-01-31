package com.softwaremind.demo.repository;

import com.softwaremind.demo.model.product.Category;
import com.softwaremind.demo.model.product.Product;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
    @Query("SELECT * FROM products WHERE category = ?0 ALLOW FILTERING")
    List<Product> findByCategory(Category category);
}
