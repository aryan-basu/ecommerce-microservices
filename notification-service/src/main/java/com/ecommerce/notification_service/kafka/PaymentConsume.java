package com.ecommerce.notification_service.kafka;

import com.ecommerce.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsume {

    private static  final Logger LOGGER= LoggerFactory.getLogger(PaymentConsume.class);
    @Autowired
    EmailService emailService;
    @KafkaListener(
            topics="${spring.kafka.topic.consume}",
            groupId ="${spring.kafka.consumer.group-id}"
    )

    public void consume(OrderEvent event) {


        LOGGER.info(String.format("Payment status of order  => %s", event.toString()));

//        LOGGER.info("Order event received for payment processing => {}", event);

        // simulate payment logic (e.g. success/failure)

        // Replace with real user email or extract from Order object
        emailService.sendOrderConfirmation("pranat.singh.31@gmail.com", event.getOrder().getOrderId(), event.getStatus());

    }

}
