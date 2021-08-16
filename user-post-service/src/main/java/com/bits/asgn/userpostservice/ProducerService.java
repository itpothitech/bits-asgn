package com.bits.asgn.userpostservice;

import java.util.logging.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    protected Logger logger = Logger.getLogger(ProducerService.class.getName());
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "TutorialTopic";

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        logger.info("Producing message: "+ message);
        this.kafkaTemplate.send(topic, message);
    }

}

