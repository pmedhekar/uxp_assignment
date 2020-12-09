package com.uxpsystems.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main Spring Boot Class
 * @author PMEDHEKA
 *
 */
@SpringBootApplication
public class SpringBootAssignmentApplication  {
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootAssignmentApplication.class);
	
	public static void main(String args[]){

		log.debug("Inside main method of SpringBootAssignmentApplication");	
		SpringApplication.run(SpringBootAssignmentApplication.class, args);
		
	}
	
}
