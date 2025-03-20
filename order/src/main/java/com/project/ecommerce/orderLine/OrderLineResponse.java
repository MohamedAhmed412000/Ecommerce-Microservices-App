package com.project.ecommerce.orderLine;

import java.math.BigDecimal;

public record OrderLineResponse(
    Integer id,
    String productName,
    String productDescription,
    BigDecimal productPrice,
    double quantity
) {}
