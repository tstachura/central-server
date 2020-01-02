package com.centralserver.kafka.consumer;

import com.centralserver.model.users.User;
import com.centralserver.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaReceiver {

    @Autowired
    private UserRepository userRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

    @KafkaListener(id = "central-user-receiver", topics = "central-topic-user", containerFactory = "userKafkaListenerContainerFactory")
    public void receive(User user) {
        userRepository.saveAndFlush(user);
        LOGGER.info("Received user: " + user.toString());
    }
}
