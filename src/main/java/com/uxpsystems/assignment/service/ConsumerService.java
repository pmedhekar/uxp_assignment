package com.uxpsystems.assignment.service;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uxpsystems.assignment.dao.UserProfile;
import com.uxpsystems.assignment.dao.UserProfileRepository;

/**
 * @author PMEDHEKA
 * Consumer Service Handling Kafka Events 
 */
@Service
public class ConsumerService {

	private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	  
	@Autowired
	private UserProfileRepository profileRepo;
	
    
    @KafkaListener(topics = SystemConstants.DELETE_USER_PROFILE_TOPIC, groupId = "group_id")
    public void consumeDeleteMessage(UserProfile userProfileToDelete) throws IOException {
    	 logger.info("####### -> Consuming message for delete user <- ####### ");
        
        Optional<UserProfile> profile = profileRepo.findById(userProfileToDelete.getId());
		if(profile.isPresent()){
			profileRepo.deleteById(userProfileToDelete.getId());
			 logger.info("####### -> Profile Deleted With User ID <- ####### "+ userProfileToDelete.getId());
		}
    }
    
   @KafkaListener(topics = SystemConstants.UPDATE_USER_PROFILE_TOPIC, groupId = "group_id")
    public void consumeUpdateMessage(UserProfile userProfileToUpdate) throws IOException {
        logger.info("####### -> Consuming message for update user <- ####### ");
        
        Optional<UserProfile> profile = profileRepo.findById(userProfileToUpdate.getId());
        
        if(profile.isPresent() && !StringUtils.isEmpty(userProfileToUpdate.getAddress())){
        
        	profile.get().setAddress(userProfileToUpdate.getAddress());
        	profileRepo.save(profile.get());
        	logger.info("####### -> Profile Updated With Address <- ####### "+ userProfileToUpdate.getAddress());
		}
        
        if(profile.isPresent() && !StringUtils.isEmpty(userProfileToUpdate.getPhone())){
        	
        	profile.get().setPhone(userProfileToUpdate.getPhone());
        	profileRepo.save(profile.get());
        	logger.info("####### -> Profile Updated With PhoneNumber <- ####### "+ userProfileToUpdate.getPhone());
		}
    }
}