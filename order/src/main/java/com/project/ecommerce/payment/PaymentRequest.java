package com.project.ecommerce.payment;

import com.project.ecommerce.customer.Customer;
import com.project.ecommerce.order.PaymentMethodEnum;

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
