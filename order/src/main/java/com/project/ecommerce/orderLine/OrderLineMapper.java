package com.project.ecommerce.orderLine;

import com.project.ecommerce.order.Order;
import com.project.ecommerce.product.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine getOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
            .order(Order.builder().id(orderLineRequest.orderId()).build())
            .productId(orderLineRequest.productId().toString())
            .quantity(orderLineRequest.quantity())
            .build();
    }

    public OrderLineResponse getOrderLineResponse(OrderLine orderLine, Product product) {
        return new OrderLineResponse(
            orderLine.getId(),
            product.name(),
            product.description(),
            product.price(),
            orderLine.getQuantity()
        );
    }
}
