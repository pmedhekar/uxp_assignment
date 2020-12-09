package com.uxpsystems.assignment.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Model for User Credentials
 * @author PMEDHEKA
 *
 */
@Entity(name="User_Credential") //specifies that the class is mapped to a database table.

public class UserCredential {
	
	@Id //specifies the primary key of an entity.
	@GeneratedValue //specifies generation strategy specification for the primary key values.
	
	private int id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	
	/**
	 *  Default Constructor
	 */
	public UserCredential(){
		
	}

	/**
	 * Parameterized Constructor
	 * @param id
	 * @param username
	 * @param password
	 */
	public UserCredential(int id, String username, String password){
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
