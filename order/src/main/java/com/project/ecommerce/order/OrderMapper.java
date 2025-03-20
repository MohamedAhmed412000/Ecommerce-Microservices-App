package com.project.ecommerce.order;

import com.project.ecommerce.orderLine.OrderLine;
import com.project.ecommerce.product.PurchaseRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order getOrder(OrderRequest orderRequest) {
        return Order.builder()
            .id(orderRequest.id())
            .reference(orderRequest.reference())
            .paymentMethod(orderRequest.paymentMethod())
            .totalAmount(orderRequest.amount())
            .customerId(orderRequest.customerId())
            .orderLines(orderRequest.products().stream().map(OrderMapper::getOrderLine).toList())
            .build();
    }

    public static OrderLine getOrderLine(PurchaseRequest purchaseRequest) {
        return OrderLine.builder()
            .productId(purchaseRequest.productId().toString())
            .quantity(purchaseRequest.quantity())
            .build();
    }

    public OrderResponse getOrderResponse(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getReference(),
            order.getTotalAmount(),
            order.getPaymentMethod(),
            order.getCustomerId()
        );
    }
}
