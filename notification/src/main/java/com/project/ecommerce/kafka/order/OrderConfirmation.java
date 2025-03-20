package com.project.ecommerce.kafka.order;

import com.project.ecommerce.kafka.payment.PaymentMethodEnum;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethodEnum paymentMethod,
    CustomerResponse customer,
    List<Product> products
) {
}
