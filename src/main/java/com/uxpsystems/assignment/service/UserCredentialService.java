package com.uxpsystems.assignment.service;

import java.util.List;
import java.util.ArrayList;

import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserCredential;
import com.uxpsystems.assignment.dao.UserCredentialRespository;

/**
 * Service class responsible for Authorizing the User by UserName and Password.
 *  
 * @author PMEDHEKA
 *
 */

@Service
public class UserCredentialService {
	
	private static final Logger log = LoggerFactory.getLogger(UserCredentialService.class);
	
	@Autowired
	private UserCredentialRespository credentialRepo;
	
	
	/**
	 *  Save Credentials in DB
	 * @param userCredential
	 */
	public void save(final UserCredential userCredential){
		credentialRepo.save(userCredential);
	}
	
	/**
	 * Fetch All Credential Records in DB
	 * @return
	 */
	public List<UserCredential> getAllCredentials() {
		 List<UserCredential> userCredentials = new ArrayList<>();
		 
		 credentialRepo.findAll().forEach(userCredential -> userCredentials.add(userCredential));
		 
		return userCredentials;
	}
	
	
	/** 
	 * Perform Login
	 * @param userCredential
	 */
	public boolean isValidCredential(final UserCredential userCredential) {
		
		if(!StringUtils.isNullOrEmpty(userCredential.getUsername()) && !StringUtils.isNullOrEmpty(userCredential.getPassword())){
			log.info("Performing Login :" + userCredential.getUsername());
			String password = credentialRepo.findByUsername(userCredential.getUsername());
			
			if(userCredential.getPassword().equals(password)){
				log.info("User Exsists in DB");
				return true;
			}
			
		}
		log.info("User Or Password Not Correct");
		return false;
	}
}
