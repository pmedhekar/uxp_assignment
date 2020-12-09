package com.uxpsystems.assignment.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.dao.UserCredential;
import com.uxpsystems.assignment.dao.UserProfile;
import com.uxpsystems.assignment.service.UserCredentialService;
import com.uxpsystems.assignment.service.UserProfileService;

/**
 *  Spring controller classes
 * @author PMEDHEKA
 *
 */
@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private static final String lOGGED_IN_COOKIE_NAME = "JSESSIONID";
	
	@Autowired
	private UserCredentialService credentialService;
	
	@Autowired
	private UserProfileService profileService;
	
	
	@RequestMapping("/")
	public String getMessage(){
		return "Server is up and Running try hitting /assignment";
	}
	
	
	@PostMapping(value = "/credenital/add")  
	public int saveCredential(@RequestBody UserCredential userCredential) {
		
		credentialService.save(userCredential);
		log.debug("User Credentials Saved Successfully with ID :" + userCredential.getId());
		
		return userCredential.getId();
		
	}
	
	@GetMapping(value = "/credenital/list", produces = "application/json")
	public List<UserCredential> getCredentials(){
	
		log.info("Fetching all Credentials From DB : ");
		
		return credentialService.getAllCredentials();
	}
	
	
	/**
	 * Perform Login.
	 * @param userCredential
	 * @param response
	 * @return
	 */
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<?> performLogin(@RequestBody @Valid UserCredential userCredential, HttpServletResponse response){
		
		if(credentialService.isValidCredential(userCredential)){
			  Cookie cookie = new Cookie(lOGGED_IN_COOKIE_NAME, userCredential.getUsername());
			  cookie.setPath("/");
			  response.addCookie(cookie);
			  
			  return new ResponseEntity<>("Login Successful, Cookie Created !! ",HttpStatus.OK);
		}
		 return new ResponseEntity<>("Username or Password are not correct",HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * Create New Profile
	 * @param profile
	 */
	@PostMapping(value = "/profile", produces = "application/json")
	public void createPersonalProfile(@CookieValue(name = lOGGED_IN_COOKIE_NAME) String loginCookie, @RequestBody @Valid UserProfile profile){
		if(loginCookie == null && isCookieValueValid(loginCookie, profile.getId())  ){
			new ResponseEntity<>("You are not Authorized to Perform Operation",HttpStatus.FORBIDDEN);
		}
		profileService.createUserProfile(profile);
	}
	
	
	/**
	 * Checks if Cookie is authorized.
	 * @param cookieValue
	 * @param id
	 * @return
	 */
	private boolean isCookieValueValid(String cookieValue, int id) {
		if(cookieValue.equals(profileService.profileCookieValue(id))){
			return true;
		}
		return false;
	}

	/**
	 * Delete Personal Profile
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/profile/{id}", produces = "application/json")
	public void removePersonalProfile(@CookieValue(name = lOGGED_IN_COOKIE_NAME) String loginCookie, @PathVariable Integer id) {
		profileService.deleteUserProfile(id);
	}
	
	/**
	 * Fetch Personal Details
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/profile/{id}", produces = "application/json")
	public ResponseEntity getPersonalProfile(@CookieValue(name = lOGGED_IN_COOKIE_NAME) String loginCookie, @PathVariable Integer id) {

		if (id == null) {
			return new ResponseEntity<>("Bad Request, Operation Not Successful", HttpStatus.BAD_REQUEST);
		}
		if(profileService.retrieveUserProfile(id) != null){
			return new ResponseEntity<>(profileService.retrieveUserProfile(id), HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Data Not Found, Operation Not Successful", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Update Personal Details
	 * @param profile
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/profile/{id}", produces = "application/json")
	public void upatePersonalProfile(@CookieValue(name = lOGGED_IN_COOKIE_NAME) String loginCookie, 
			@RequestBody @Valid UserProfile profile, @PathVariable Integer id) {
		
		
		if(!StringUtils.isNullOrEmpty(profile.getAddress())){
			profileService.updateProfileAddress(id, profile.getAddress());
		}
		
		if(null != profile.getPhone()){
			profileService.updateProfilePhoneNumber(id, profile.getPhone());
		}
	}
}
