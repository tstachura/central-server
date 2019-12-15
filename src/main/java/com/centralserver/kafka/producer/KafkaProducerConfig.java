package com.centralserver.kafka.producer;

import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;
import com.centralserver.model.users.Address;
import com.centralserver.model.users.User;
import com.centralserver.model.users.Userdata;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    //Product

    @Bean
    public ProducerFactory<String, Product> productProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Product> productKafkaTemplate() {
        return new KafkaTemplate<>(productProducerFactory());
    }

    //ProductType

    @Bean
    public ProducerFactory<String, ProductType> productTypeProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, ProductType> productTypeKafkaTemplate() {
        return new KafkaTemplate<>(productTypeProducerFactory());
    }

    // User

    @Bean
    public ProducerFactory<String, User> userProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, User> userKafkaTemplate() {
        return new KafkaTemplate<>(userProducerFactory());
    }

    //Userdata

    @Bean
    public ProducerFactory<String, Userdata> userdataProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Userdata> userdataKafkaTemplate() {
        return new KafkaTemplate<>(userdataProducerFactory());
    }

    //Address

    @Bean
    public ProducerFactory<String, Address> addressProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Address> addressKafkaTemplate() {
        return new KafkaTemplate<>(addressProducerFactory());
    }

    @Bean
    public KafkaProducer kafkaProducer() {
        return new KafkaProducer();
    }
}
