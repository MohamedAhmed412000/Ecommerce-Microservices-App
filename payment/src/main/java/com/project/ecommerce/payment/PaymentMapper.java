package com.project.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment getPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
            .id(paymentRequest.id())
            .amount(paymentRequest.amount())
            .paymentMethod(paymentRequest.paymentMethod())
            .orderId(paymentRequest.orderId())
            .build();
    }
}
