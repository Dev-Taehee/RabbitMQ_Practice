package com.example.StockOrderSystem.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // 한국 주식 주문 시스템으로의 메시지 키 값
    @Value("${spring.rabbitmq.koreaStockQueue}")
    private String koreaStockQueue;

    @Value("${spring.rabbitmq.koreaStockExchange}")
    private String koreaStockExchange;

    @Value("${spring.rabbitmq.koreaStockRoutingKey}")
    private String koreaStockRoutingKey;

    // 미국 주식 주문 시스템으로의 메시지 키 값
    @Value("${spring.rabbitmq.usaStockQueue}")
    private String usaStockQueue;

    @Value("${spring.rabbitmq.usaStockExchange}")
    private String usaStockExchange;

    @Value("${spring.rabbitmq.usaStockRoutingKey}")
    private String usaStockRoutingKey;

    // 한국 주식 주문 시스템으로의 메시지 설정
    @Bean
    public Queue koreaStockQueue() {
        return new Queue(koreaStockQueue);
    }

    @Bean
    public DirectExchange koreaStockExchange() {
        return new DirectExchange(koreaStockExchange);
    }

    @Bean
    public Binding koreaStockBinding() {
        return BindingBuilder.bind(koreaStockQueue()).to(koreaStockExchange()).with(koreaStockRoutingKey);
    }

    // 미국 주식 주문 시스템으로의 메시지 설정
    @Bean
    public Queue usaStockQueue() {
        return new Queue(usaStockQueue);
    }

    @Bean
    public DirectExchange usaStockExchange() {
        return new DirectExchange(usaStockExchange);
    }

    @Bean
    public Binding usaStockBinding() {
        return BindingBuilder.bind(usaStockQueue()).to(usaStockExchange()).with(usaStockRoutingKey);
    }

    // JSON 형식으로 메시지를 주고 받기 위한 설정
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
