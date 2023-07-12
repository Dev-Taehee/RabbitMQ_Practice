package com.example.KoreaStock.consumer;

import com.example.KoreaStock.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class KoreaStockConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KoreaStockConsumer.class);

    @RabbitListener(queues = {"${spring.rabbitmq.koreaStockQueue}"})
    public void consumeJsonMessage(Order order) {
        LOGGER.info(String.format("Received JSON message from KoreaStockQueue -> %s", order.toString()));
    }

}
