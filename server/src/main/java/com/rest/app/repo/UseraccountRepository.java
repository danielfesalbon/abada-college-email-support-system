/**
 * 
 */
package com.rest.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.app.table.Useraccount;

/**
 * @author danielf
 *
 */
public interface UseraccountRepository extends JpaRepository<Useraccount, Long> {

	@Query("SELECT u FROM Useraccount u WHERE u.username = ?1")
	Useraccount findByUsername(String username);

}
