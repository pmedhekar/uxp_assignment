package com.uxpsystems.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserProfile;

/**
 * @author PMEDHEKA
 *  Producer Service Handling Kafka Events 
 */
@Service
@EnableKafka
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
   
    @Autowired
    private KafkaTemplate<String, UserProfile> kafkaTemplate;
    
 
    public void sendDeleteUserProfileMessage(UserProfile userProfile) {
    	logger.info("####### -> Producing message for delete user <- ####### ");
        this.kafkaTemplate.send(SystemConstants.DELETE_USER_PROFILE_TOPIC, userProfile);
    }

    public void sendUpdateProfileMessage(UserProfile userProfile) {
    	logger.info("####### -> Producing message for update user <- ####### ");
        this.kafkaTemplate.send(SystemConstants.UPDATE_USER_PROFILE_TOPIC, userProfile);
    }
    
}