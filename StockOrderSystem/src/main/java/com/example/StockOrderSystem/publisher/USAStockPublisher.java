package com.example.StockOrderSystem.publisher;

import com.example.StockOrderSystem.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class USAStockPublisher {

    @Value("${spring.rabbitmq.usaStockExchange}")
    private String usaStockExchange;

    @Value("${spring.rabbitmq.usaStockRoutingKey}")
    private String usaStockRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(USAStockPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public USAStockPublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Order order) {
        LOGGER.info(String.format("USA Stock Order Message sent -> %s", order.toString()));
        rabbitTemplate.convertAndSend(usaStockExchange, usaStockRoutingKey, order);
    }

}
