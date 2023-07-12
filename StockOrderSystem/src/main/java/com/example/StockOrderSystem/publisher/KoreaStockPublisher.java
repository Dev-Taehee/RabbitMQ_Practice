package com.example.StockOrderSystem.publisher;

import com.example.StockOrderSystem.config.RabbitMQConfig;
import com.example.StockOrderSystem.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KoreaStockPublisher {

    @Value("${spring.rabbitmq.koreaStockExchange}")
    private String koreaStockExchange;

    @Value("${spring.rabbitmq.koreaStockRoutingKey}")
    private String koreaStockRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(KoreaStockPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public KoreaStockPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Order order) {
        LOGGER.info(String.format("Korea Stock Order Message sent -> %s", order.toString()));
        rabbitTemplate.convertAndSend(koreaStockExchange, koreaStockRoutingKey, order);
    }

}
