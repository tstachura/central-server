package com.centralserver.kafka.producer;

import com.centralserver.kafka.consumer.receivers.KafkaReceiver;
import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;
import com.centralserver.model.users.Address;
import com.centralserver.model.users.User;
import com.centralserver.model.users.Userdata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, User> userTemplate;

    @Autowired
    private KafkaTemplate<String, Userdata> userdataTemplate;

    @Autowired
    private KafkaTemplate<String, Product> productTemplate;

    @Autowired
    private KafkaTemplate<String, ProductType> productTypeKafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);


    @Value(value = "${kafka.topic.unit}")
    private String unitTopic;

    public void send(User user) {
        userTemplate.send(unitTopic, user);
        LOGGER.info("Produce user" + user.toString());
    }

    public void send(Userdata userdata) {
        userdataTemplate.send(unitTopic, userdata);
        LOGGER.info("Produce userdata" + userdata.toString());
    }

    public void send(Product product) {
        productTemplate.send(unitTopic, product);
        LOGGER.info("Produce product" + product.getSerialNumber());
    }

    public void send(ProductType productType) {
        productTypeKafkaTemplate.send(unitTopic, productType);
        LOGGER.info("Produce product type" + productType.toString());
    }
}
