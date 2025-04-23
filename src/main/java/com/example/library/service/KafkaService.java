package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaService {

    @Autowired
    LoanService loanService;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC_NAME= "library";

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
        System.out.println("Send id for return loan: " +  message);
    }
    
    @KafkaListener(topics = TOPIC_NAME, groupId = "library-group-id")
    public void consumeMessage(String message) {

        System.out.println("Received id for return loan: " + message);
        loanService.returnLoan(Integer.parseInt(message));

    }


}