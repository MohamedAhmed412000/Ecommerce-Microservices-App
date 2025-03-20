package com.project.ecommerce.product;

import java.math.BigDecimal;

public record Product(
    String productId,
    String name,
    String description,
    BigDecimal price,
    double quantity
) {
}
