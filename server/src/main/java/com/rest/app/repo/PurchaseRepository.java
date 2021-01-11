/**
 * 
 */
package com.rest.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.app.table.Productpertransaction;

/**
 * @author danielf
 *
 */
public interface PurchaseRepository extends JpaRepository<Productpertransaction, Long> {

}
