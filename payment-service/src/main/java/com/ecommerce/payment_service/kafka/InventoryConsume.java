package com.ecommerce.payment_service.kafka;


import com.ecommerce.base_domains.dto.Order;
import com.ecommerce.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsume {





    private final PaymentProducer paymentProducer;

    public InventoryConsume(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }



    private static  final Logger LOGGER= LoggerFactory.getLogger(InventoryConsume.class);
    @KafkaListener(
            topics="${spring.kafka.topic.consume}",
            groupId ="${spring.kafka.consumer.group-id}"
    )

    public void consume(OrderEvent event) {


        LOGGER.info(String.format("Order status of  inverntory service => %s", event.toString()));

        LOGGER.info("Order event received for payment processing => {}", event);

        // simulate payment logic (e.g. success/failure)
        paymentProducer.sendPaymentStatus(event);
    }
}
