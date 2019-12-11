package com.centralserver.kafka;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KafkaSyncMessage {

    Object entity;
    KafkaMessageAction action;
}
