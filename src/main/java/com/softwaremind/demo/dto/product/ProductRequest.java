package com.softwaremind.demo.dto.product;

import com.softwaremind.demo.model.product.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    String name;
    String description;
    BigDecimal price;
    Category category;
}
