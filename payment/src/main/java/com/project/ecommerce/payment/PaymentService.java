package com.project.ecommerce.payment;

import com.project.ecommerce.notification.NotificationProducer;
import com.project.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.getPayment(paymentRequest));

        // send payment notification to notification-ms (through Kafka)
        notificationProducer.sendPaymentNotification(new PaymentNotificationRequest(
            paymentRequest.orderReference(),
            paymentRequest.amount(),
            paymentRequest.paymentMethod(),
            paymentRequest.customer().firstname(),
            paymentRequest.customer().lastname(),
            paymentRequest.customer().email()
        ));

        return payment.getId();
    }
}
