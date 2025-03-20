package com.project.ecommerce.kafka;

import com.project.ecommerce.email.EmailService;
import com.project.ecommerce.kafka.order.OrderConfirmation;
import com.project.ecommerce.kafka.payment.PaymentConfirmation;
import com.project.ecommerce.notification.Notification;
import com.project.ecommerce.notification.NotificationRepository;
import com.project.ecommerce.notification.NotificationTypeEnum;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Received payment confirmation: {}", paymentConfirmation);

        // first we save the notification
        notificationRepository.save(
            Notification.builder()
                .notificationType(NotificationTypeEnum.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build()
        );

        // Send notification
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
            paymentConfirmation.customerEmail(),
            customerName,
            paymentConfirmation.amount(),
            paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Received order confirmation: {}", orderConfirmation);

        // first we save the notification
        notificationRepository.save(
            Notification.builder()
                .notificationType(NotificationTypeEnum.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build()
        );

        // Send notification
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
            orderConfirmation.customer().email(),
            customerName,
            orderConfirmation.totalAmount(),
            orderConfirmation.orderReference(),
            orderConfirmation.products()
        );

    }

}
