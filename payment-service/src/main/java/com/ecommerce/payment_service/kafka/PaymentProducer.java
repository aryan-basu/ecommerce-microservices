package com.ecommerce.payment_service.kafka;

import com.ecommerce.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    @Value("${spring.kafka.topic.produce}")
    private String topicName;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProducer.class);

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentStatus(OrderEvent event) {
        event.setStatus("PAYMENT_COMPLETED");
        event.setMessage("Payment successfully completed");

        LOGGER.info("Sending payment status => {}", event);
        kafkaTemplate.send(topicName, event);
    }
}
