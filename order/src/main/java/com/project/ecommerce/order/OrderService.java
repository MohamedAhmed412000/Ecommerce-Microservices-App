package com.project.ecommerce.order;

import com.project.ecommerce.customer.CustomerClient;
import com.project.ecommerce.exceptions.BusinessException;
import com.project.ecommerce.kafka.OrderConfirmation;
import com.project.ecommerce.kafka.OrderProducer;
import com.project.ecommerce.orderLine.OrderLineRequest;
import com.project.ecommerce.orderLine.OrderLineService;
import com.project.ecommerce.payment.PaymentClient;
import com.project.ecommerce.payment.PaymentRequest;
import com.project.ecommerce.product.ProductClient;
import com.project.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        // Check that the customer exists --> OpenFeign
        var customer = customerClient.findCustomerById(orderRequest.customerId())
            .orElseThrow(() -> new BusinessException("Can't Create Order :: Customer Not Found"));

        // purchase the products --> RestTemplate
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        // save the order and order lines --> JPA
        var order = orderRepository.save(orderMapper.getOrder(orderRequest));
        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.createOrderLine(new OrderLineRequest(
                null,
                order.getId(),
                purchaseRequest.productId(),
                purchaseRequest.quantity()
            ));
        }

        // start the payment process
        paymentClient.requestOrderPayment(new PaymentRequest(
            null,
            orderRequest.amount(),
            orderRequest.paymentMethod(),
            order.getId().toString(),
            order.getReference(),
            customer
        ));

        // send order confirmation to customer using Kafka (Notification Microservice)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
            orderRequest.reference(),
            orderRequest.amount(),
            orderRequest.paymentMethod(),
            customer,
            purchasedProducts
        ));

        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
           .stream()
           .map(orderMapper::getOrderResponse)
           .toList();
    }

    public OrderResponse getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
           .map(orderMapper::getOrderResponse)
           .orElseThrow(() -> new EntityNotFoundException("Order Not Found with id = " + orderId));
    }
}
