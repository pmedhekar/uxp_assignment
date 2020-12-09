package com.uxpsystems.assignment.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD Operations related to UserProfile
 * @author PMEDHEKA
 *
 */
@Repository
public interface UserProfileRepository  extends CrudRepository<UserProfile, Integer>{

}
