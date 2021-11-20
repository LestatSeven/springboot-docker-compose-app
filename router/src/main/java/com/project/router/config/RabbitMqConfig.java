package com.project.router.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMqConfig implements RabbitListenerConfigurer {

    @Bean
    Queue testQueue() {
        return new Queue("test-queue", false);
    }

    @Bean
    Queue reportsQueue() {
        return new Queue("reports-queue", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("com-project-system");
    }

    @Bean
    Binding dataBinding(@Qualifier("reportsQueue") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("ReportingConfig");
    }

    @Bean
    MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean
    DefaultMessageHandlerMethodFactory jsonMessageHandlerMethod() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(jackson2Converter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(jsonMessageHandlerMethod());
    }
}
