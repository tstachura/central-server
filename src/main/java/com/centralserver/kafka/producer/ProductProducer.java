package com.centralserver.kafka.producer;

import com.centralserver.kafka.consumer.receivers.ProductReceiver;
import com.centralserver.model.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class ProductProducer {

    @Autowired
    private KafkaTemplate<String, Product> productTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReceiver.class);


    @Value(value = "${kafka.topic.central}")
    private String unitTopic;

    public void send(Product product) {
        productTemplate.send(unitTopic, product);
        LOGGER.info("Produce product" + product.toString());
    }
}
