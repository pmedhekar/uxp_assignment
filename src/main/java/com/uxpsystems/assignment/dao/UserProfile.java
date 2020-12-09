package com.uxpsystems.assignment.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Model for User Profile Details 
 * @author PMEDHEKA
 *
 */
@Entity(name="User_Profile") // specifies that the class is mapped to a database table.
public class UserProfile {
	
	@Id //specifies the primary key of an entity.
	@GeneratedValue //specifies generation strategy specification for the primary key values.
	
	private int id;
	private String firstname;
	private String lastname;
	private String address;
	private Integer phone;

	public UserProfile() {

	}

	public UserProfile(int id, String firstName, String lastName, String address, Integer phone) {
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstname;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastname;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public Integer getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
