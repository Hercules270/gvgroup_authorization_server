package com.gvgroup.usermanagement.listener;


import com.gvgroup.usermanagement.model.message.UserCreatedMessage;
import com.gvgroup.usermanagement.model.message.UserDeletedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.gvgroup.usermanagement.util.KafkaConstants.USER_CREATED_TOPIC_NAME;
import static com.gvgroup.usermanagement.util.KafkaConstants.USER_DELETED_TOPIC_NAME;


@Slf4j
@Component
public class KafkaQueueListener {

    @KafkaListener(topics = USER_CREATED_TOPIC_NAME)
    public void listenUserCreated(UserCreatedMessage message) {
        log.info("User created message received {}", message);
    }

    @KafkaListener(topics = USER_DELETED_TOPIC_NAME)
    public void listenUserDeleted(UserDeletedMessage message) {
        log.info("User deleted message received {}", message);
    }

}
