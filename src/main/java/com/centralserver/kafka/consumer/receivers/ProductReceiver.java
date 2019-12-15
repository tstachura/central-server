package com.centralserver.kafka.consumer.receivers;

import java.util.concurrent.CountDownLatch;

import com.centralserver.model.products.Product;
import com.centralserver.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class ProductReceiver {

   /* private final String id = "productReceiver";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReceiver.class);

    private CountDownLatch latch = new CountDownLatch(1);


    public CountDownLatch getLatch() {
        return latch;
    }

    @Autowired
    private ProductRepository productRepository;

    @KafkaListener(id = id, topics = "kafka.topic.central", containerFactory = "productKafkaListenerContainerFactory")
    public void receive(Product product) {
        productRepository.saveAndFlush(product);
        LOGGER.info("Received c1");
    }*/
}
