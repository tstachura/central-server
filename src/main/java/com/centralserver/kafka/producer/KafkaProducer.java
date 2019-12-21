package com.centralserver.kafka.producer;

import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;
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
    private KafkaTemplate<String, Product> productTemplate;

    @Autowired
    private KafkaTemplate<String, ProductType> productTypeKafkaTemplate;

    @Value(value = "${kafka.topic.unit.user}")
    private String unitTopicUser;

    @Value(value = "${kafka.topic.unit.product}")
    private String unitTopicProduct;

    @Value(value = "${kafka.topic.unit.product.type}")
    private String unitTopicProductType;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    public void send(User user) {
        userTemplate.send(unitTopicUser, user);
        LOGGER.info("Produce user" + user.toString());
    }

    public void send(Product product) {
        productTemplate.send(unitTopicProduct, product);
        LOGGER.info("Produce product" + product.getSerialNumber());
    }

    public void send(ProductType productType) {
        productTypeKafkaTemplate.send(unitTopicProductType, productType);
        LOGGER.info("Produce product type" + productType.toString());
    }
}
