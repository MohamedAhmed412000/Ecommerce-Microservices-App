package com.project.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
    Integer id,
    @NotNull(message = "Product name is required")
    String name,
    @NotNull(message = "Product description is required")
    String description,
    @Positive(message = "quantity value must be positive number")
    double availableQuantity,
    @Positive(message = "Price value must be positive number")
    BigDecimal price,
    @NotNull(message = "Product category is required")
    Integer categoryId
) {
}
