package com.project.ecommerce.kafka;

import com.project.ecommerce.customer.Customer;
import com.project.ecommerce.order.PaymentMethodEnum;
import com.project.ecommerce.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethodEnum paymentMethod,
    Customer customer,
    List<Product> products
) {
}
