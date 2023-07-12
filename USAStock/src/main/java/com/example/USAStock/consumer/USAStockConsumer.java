package com.example.USAStock.consumer;

import com.example.USAStock.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class USAStockConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(USAStockConsumer.class);

    @RabbitListener(queues = {"${spring.rabbitmq.usaStockQueue}"})
    public void consumeJsonMessage(Order order) {
        LOGGER.info(String.format("Received JSON message from usaStockQueue -> %s", order.toString()));
    }

}
