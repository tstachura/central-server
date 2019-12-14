package com.centralserver.kafka.producer;

import com.centralserver.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class ProductProducer {

    @Autowired
    private KafkaTemplate<String, Product> productTemplate;

    @Value(value = "${kafka.topic.central}")
    private String unitTopic;

    public void send(Product product) {
        productTemplate.send(unitTopic, product);
    }
}
