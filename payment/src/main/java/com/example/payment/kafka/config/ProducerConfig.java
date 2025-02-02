package com.example.payment.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ProducerConfig {
    @Bean
    public NewTopic invalidRequest() {
        return TopicBuilder.name("invalidRequest").build();
    }
    @Bean
    public NewTopic insufficientAmount() {
        return TopicBuilder.name("insufficientAmount").build();
    }
    @Bean
    public NewTopic paymentSuccess() {
        return TopicBuilder.name("paymentSuccess").build();
    }
    @Bean
    public NewTopic missingPaymentMethod() {
        return TopicBuilder.name("missingPaymentMethod").build();
    }
    @Bean
    public NewTopic unconfirmedOrder() {
        return TopicBuilder.name("unconfirmedOrder").build();
    }
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
