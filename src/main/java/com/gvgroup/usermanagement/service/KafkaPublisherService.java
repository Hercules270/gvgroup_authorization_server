package com.gvgroup.usermanagement.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPublisherService implements MessagePublisherService {

    private final KafkaTemplate kafkaTemplate;

    @Override
    public void publish(String topic, Object message) {
        kafkaTemplate.send(topic, message);
    }
}
