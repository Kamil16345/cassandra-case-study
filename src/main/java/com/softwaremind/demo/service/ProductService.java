package com.softwaremind.demo.service;

import com.softwaremind.demo.dto.ProductRequest;
import com.softwaremind.demo.dto.ProductResponse;
import com.softwaremind.demo.exception.ProductNotFoundException;
import com.softwaremind.demo.model.Product;
import com.softwaremind.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .build();
        productRepository.save(product);
        log.info("Product has been saved: " + product.getId());
    }

    public ProductResponse getProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        return mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public ResponseEntity<ProductResponse> updateProduct(UUID id, Product body) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        existingProduct.setName(body.getName());
        existingProduct.setDescription(body.getDescription());
        existingProduct.setPrice(body.getPrice());
        existingProduct.setCategory(body.getCategory());
        productRepository.save(existingProduct);
        log.info("Product has been updated: " + existingProduct.getId());
        return ResponseEntity.status(HttpStatus.OK).body(mapToProductResponse(existingProduct));
    }

    public ResponseEntity<String> deleteProduct(UUID id){
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}
