package com.centralserver.kafka.consumer.receivers;

import java.util.concurrent.CountDownLatch;

import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;
import com.centralserver.model.users.Address;
import com.centralserver.model.users.User;
import com.centralserver.model.users.Userdata;
import com.centralserver.repositories.ProductRepository;
import com.centralserver.repositories.ProductTypeRepository;
import com.centralserver.repositories.UserRepository;
import com.centralserver.repositories.UserdataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaReceiver {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserdataRepository userdataRepository;

    private final String id = "central-receiver";

    private final String topic = "central-topic";

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

    private CountDownLatch latch = new CountDownLatch(1);


    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(id = id, topics = topic, containerFactory = "kafkaListenerContainerFactory")
    public void receive(User user) {
        userRepository.saveAndFlush(user);
        LOGGER.info("Received user" + user.toString());
    }

    @KafkaListener(id = id, topics = topic, containerFactory = "kafkaListenerContainerFactory")
    public void receive(Userdata userdata) {
        userdataRepository.saveAndFlush(userdata);
        LOGGER.info("Received user" + userdata.toString());
    }

    @KafkaListener(id = id, topics = topic, containerFactory = "kafkaListenerContainerFactory")
    public void receive(Product product) {
        productRepository.saveAndFlush(product);
        LOGGER.info("Received product" + product.toString());
    }

    @KafkaListener(id = id, topics = topic, containerFactory = "kafkaListenerContainerFactory")
    public void receive(ProductType productType) {
        productTypeRepository.saveAndFlush(productType);
        LOGGER.info("Received product" + productType.toString());
    }
}
