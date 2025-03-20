package com.project.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
    String orderReference,
    BigDecimal amount,
    PaymentMethodEnum paymentMethod,
    String customerFirstname,
    String customerLastname,
    String customerEmail
) {
}
