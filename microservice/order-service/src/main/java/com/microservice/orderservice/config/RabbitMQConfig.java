package com.microservice.orderservice.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import static com.microservice.orderservice.util.QueueUtil.EXCHANGE;
import static com.microservice.orderservice.util.QueueUtil.QUEUE;

@Component
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final AmqpAdmin amqpAdmin;

    private Queue queue(String queue){
        return new Queue(queue, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(),null);
    }

    @PostConstruct
    private void createCall(){
        Queue queue = queue(QUEUE);
        DirectExchange directExchange = directExchange();

        Binding binding = binding(queue, directExchange);

        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);

    }

}
