/**
 * 
 */
package com.rest.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.rest.app.table.Audittrail;
import com.rest.app.table.Useraccount;
import com.rest.app.util.PaginateValues;
import com.rest.app.util.SystemProperties;

/**
 * @author danielf
 *
 */
public interface SystemService {

	ResponseEntity<SystemProperties> getProperties();

	List<Audittrail> getAudittrail(Integer row, Integer page);

	ResponseEntity<PaginateValues> getPageValues(int row);

	ResponseEntity<Map<String, Object>> validateReset(Useraccount user);

	ResponseEntity<Map<String, Object>> resetPassword(Useraccount user);
}
