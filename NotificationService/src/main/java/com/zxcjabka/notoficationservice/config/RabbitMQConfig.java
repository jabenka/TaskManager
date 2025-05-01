package com.zxcjabka.notoficationservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange("task.exchange", true, false);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue("task.expiry.notifications", true, false, false);
    }

    @Bean
    public Binding binding(Queue notificationQueue, TopicExchange taskExchange) {
        return BindingBuilder
                .bind(notificationQueue)
                .to(taskExchange)
                .with("expiry.notification");
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }
}
