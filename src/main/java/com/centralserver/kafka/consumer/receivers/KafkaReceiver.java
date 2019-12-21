package com.centralserver.kafka.consumer.receivers;

import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;
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

    private final String topic = "unit-topic";

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

    //    @KafkaHandler
    @KafkaListener(id = "central-user-receiver", topics = topic, containerFactory = "userKafkaListenerContainerFactory")
    public void receive(User user) {
//        userRepository.saveAndFlush(user);
        LOGGER.info("Received user: " + user.toString());
    }

    //    @KafkaHandler
    @KafkaListener(id = "central-userdata-receiver", topics = topic, containerFactory = "userdataKafkaListenerContainerFactory")
    public void receive(Userdata userdata) {
//        userdataRepository.saveAndFlush(userdata);
        LOGGER.info("Received userdata: " + userdata.toString());
    }

    //    @KafkaHandler
    @KafkaListener(id = "central-product-receiver", topics = topic, containerFactory = "productKafkaListenerContainerFactory")
    public void receive(Product product) {
//        productRepository.saveAndFlush(product);
        LOGGER.info("Received product: " + product.toString());
    }

    //    @KafkaHandler
    @KafkaListener(id = "central-product-type-receiver", topics = topic, containerFactory = "productTypeKafkaListenerContainerFactory")
    public void receive(ProductType productType) {
//        productTypeRepository.saveAndFlush(productType);
        LOGGER.info("Received product type: " + productType.toString());
    }
}
