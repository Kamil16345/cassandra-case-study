package com.softwaremind.demo.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
@Table("products")
public class Product {
    @PrimaryKey
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
}
