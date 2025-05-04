package com.ecommerce.inventory_service.kafka;


import com.ecommerce.base_domains.dto.Order;
import com.ecommerce.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.produce}")
    private String inventoryCheckedTopic;

    public OrderConsumer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private static  final Logger LOGGER= LoggerFactory.getLogger(OrderConsumer.class);
    @KafkaListener(
            topics="${spring.kafka.topic.consume}",
            groupId ="${spring.kafka.consumer.group-id}"
    )

    public void consume(OrderEvent event){

        LOGGER.info(String.format("Order event received in stock service => %s",event.toString()));

        boolean inStock = checkInventory(event.getOrder());

        // 🔁 Create updated event
        OrderEvent inventoryEvent = new OrderEvent();
        inventoryEvent.setOrder(event.getOrder());
        inventoryEvent.setMessage(inStock ? "Stock available" : "Out of stock");
        inventoryEvent.setStatus(inStock ? "IN_STOCK" : "OUT_OF_STOCK");

        // 🚀 Publish to inventory_checked topic
        kafkaTemplate.send(inventoryCheckedTopic, inventoryEvent);
        LOGGER.info("Inventory check event sent => {}", inventoryEvent);
    }

    private boolean checkInventory(Order order) {
        // 🧠 Mock inventory logic: assume everything with qty <= 10 is in stock
        return order.getQty() <= 10;
    }
}
