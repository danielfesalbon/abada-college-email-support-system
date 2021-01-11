/**
 * 
 */
package com.rest.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.app.table.Audittrail;

/**
 * @author danielf
 *
 */
public interface AudittrailRepository extends JpaRepository<Audittrail, Long> {

}
