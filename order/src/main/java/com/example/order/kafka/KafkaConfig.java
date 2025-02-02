package com.example.order.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic orderPlacedTopic() {
        return TopicBuilder.name("orderPlaced")
                .build();
    }

    @Bean
    public NewTopic orderProcessed() {
        return TopicBuilder.name("orderProcessed")
                .build();
    }
    @Bean
    KafkaTemplate<String,Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
