package com.project.ecommerce.payment;

import java.math.BigDecimal;

public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    PaymentMethodEnum paymentMethod,
    String orderId,
    String orderReference,
    Customer customer
) {
}
