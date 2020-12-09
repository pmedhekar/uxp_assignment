package com.uxpsystems.assignment.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * DAO interface that extends JPA repo which handles CRUD operations.
 * @author PMEDHEKA
 *
 */
@Repository
public interface UserCredentialRespository extends CrudRepository<UserCredential, Integer> {
	
	@Query("SELECT t.password FROM User_Credential t where t.username = :username")
	String findByUsername(@Param("username") String username);
	
}
