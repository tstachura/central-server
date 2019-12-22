package com.centralserver.kafka;

import com.centralserver.kafka.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;


public class KafkaMessageCache {

    private static Deque<Object> cachedMessages = new ArrayDeque<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    public static Object pull() {
        return cachedMessages.pop();
    }

    public static Deque<Object> getCachedMessages() {
        return cachedMessages;
    }

    public static void put(Object message) {
        cachedMessages.push(message);
        LOGGER.info("Message with " + message.getClass().getName() + " : " + message.toString() + " is cached");
    }
}
