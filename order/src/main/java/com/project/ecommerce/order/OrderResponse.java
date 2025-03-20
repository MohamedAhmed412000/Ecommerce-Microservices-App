package com.project.ecommerce.order;

import java.math.BigDecimal;

public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethodEnum paymentMethod,
    String customerId
) {
}
