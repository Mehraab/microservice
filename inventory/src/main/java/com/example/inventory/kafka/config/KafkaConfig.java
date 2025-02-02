package com.example.inventory.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic orderNotFoundTopic() {
        return TopicBuilder.name("orderNotFound").build();
    }
    @Bean
    public NewTopic orderConfirmedTopic() {
        return TopicBuilder.name("orderConfirmed").build();
    }
    @Bean
    public NewTopic outOfStockTopic() {
        return TopicBuilder.name("outOfStock").build();
    }
    @Bean
    KafkaTemplate<String,Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
//
//    @Bean
//    public ConsumerFactory<String, OrderDto> consumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"[::1]:9092");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, com.fasterxml.jackson.databind.deser.std.StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(config);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory() {
//        ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory();
//        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
//        return kafkaListenerContainerFactory;
//    }
}
