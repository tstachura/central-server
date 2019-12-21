package com.centralserver.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic centralTopicUser() {
        return new NewTopic("${kafka.topic.central.user}", 1, (short) 1);
    }

    @Bean
    public NewTopic unitTopicUser() {
        return new NewTopic("${kafka.topic.unit.user}", 1, (short) 1);
    }

    @Bean
    public NewTopic unitTopicProduct() {
        return new NewTopic("${kafka.topic.unit.product}", 1, (short) 1);
    }

    @Bean
    public NewTopic unitTopicProductType() {
        return new NewTopic("${kafka.topic.unit.product.type}", 1, (short) 1);
    }
}
