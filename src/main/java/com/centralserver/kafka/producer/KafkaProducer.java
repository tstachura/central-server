package com.centralserver.kafka.producer;

import com.centralserver.kafka.KafkaMessageCache;
import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;
import com.centralserver.model.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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

    @Async
    public void send(User user) {
        handleResult(userTemplate.send(unitTopicUser, user), user);
    }

    @Async
    public void send(Product product) {
        handleResult(productTemplate.send(unitTopicProduct, product), product);
    }

    @Async
    public void send(ProductType productType) {
        handleResult(productTypeKafkaTemplate.send(unitTopicProductType, productType), productType);
    }

    private <T> void handleResult(ListenableFuture<SendResult<String, T>> future, T message) {
        future.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onSuccess(SendResult<String, T> result) {
                LOGGER.info("Sent message = [ "
                        + message.getClass().getSimpleName() + " : " + message.toString()
                        + " ] with offset=[ " + result.getRecordMetadata().offset() + " ]");
            }

            @Override
            public void onFailure(Throwable ex) {
                KafkaMessageCache.put(message);
                LOGGER.warn("Unable to send message =[ "
                        + message.getClass().getSimpleName() + " : "
                        + message.toString() + " ] due to : " + ex.getMessage());
            }
        });
    }
}
