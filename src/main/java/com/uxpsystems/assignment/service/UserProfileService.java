package com.uxpsystems.assignment.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserProfile;
import com.uxpsystems.assignment.dao.UserProfileRepository;

/**
 * Service will save profile informations such as Address and Phone number
 * 
 * @author PMEDHEKA
 *
 */
@Service
public class UserProfileService {

	private static final Logger log = LoggerFactory.getLogger(UserProfileService.class);

	@Autowired
	private UserProfileRepository profileRepo;

	@Autowired
	private ProducerService producerService;

	/**
	 * Add New Profile Details REST Sync Communication to update DB
	 * 
	 * @param profile
	 */
	public void createUserProfile(UserProfile profile) {
		log.debug("Executing UserProfileService createUserProfile");
		profileRepo.save(profile);
	}

	/**
	 * Get Profile Details
	 * 
	 * @param id
	 * @return
	 */
	public UserProfile retrieveUserProfile(int id) {
		Optional<UserProfile> profile = profileRepo.findById(id);
		if (profile.isPresent()) {
			return profile.get();
		}
		return null;
	}

	/**
	 * Deletes User Profile By ID
	 * 
	 * @param id
	 * @return
	 */
	public void deleteUserProfile(int id) {
		log.debug("Executing UserProfileService deleteUserProfile");

		UserProfile userProfile = new UserProfile();
		userProfile.setId(id);
		producerService.sendDeleteUserProfileMessage(userProfile);
	}

	/**
	 * Update Profile Address
	 * 
	 * @param id
	 * @param newAddress
	 * @return
	 */
	public void updateProfileAddress(int id, String newAddress) {
		log.info("Executing UserProfileService updateProfileAddress");
		
		UserProfile userProfile = new UserProfile();
		userProfile.setId(id);
		userProfile.setAddress(newAddress);
		producerService.sendUpdateProfileMessage(userProfile);
	}

	/**
	 * @param id
	 * @param newPhoneNumber
	 * @return
	 */
	public void updateProfilePhoneNumber(int id, Integer newPhoneNumber) {
		log.info("Executing UserProfileService updateProfileAddress");

		UserProfile userProfile = new UserProfile();
		userProfile.setId(id);
		userProfile.setPhone(newPhoneNumber);
		producerService.sendUpdateProfileMessage(userProfile);
		
	}

	public String profileCookieValue(int id ){
		
		Optional<UserProfile> profile = profileRepo.findById(id);
		if (profile.isPresent()) {
			return profile.get().getFirstName().toLowerCase()+profile.get().getLastName().toLowerCase();
		}
		return null;
	}
}
