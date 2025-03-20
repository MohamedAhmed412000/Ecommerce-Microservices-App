package com.project.ecommerce.notification;

import com.project.ecommerce.payment.PaymentMethodEnum;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethodEnum paymentMethod,
    String customerFirstname,
    String customerLastname,
    String customerEmail
) {
}
