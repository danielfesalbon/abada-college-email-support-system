/**
 * 
 */
package com.rest.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.app.service.SystemService;
import com.rest.app.table.Audittrail;
import com.rest.app.table.Useraccount;
import com.rest.app.util.PaginateValues;
import com.rest.app.util.SystemProperties;

/**
 * @author danielf
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/system")
@Component
public class SystemController {

	@Autowired
	private SystemService systemService;

	@GetMapping("/properties")
	public ResponseEntity<SystemProperties> getProperties() {
		return systemService.getProperties();
	}

	@GetMapping("/activity")
	public List<Audittrail> getAudittrail(@RequestParam(required = false, name = "row") Integer row,
			@RequestParam(required = false, name = "page") Integer page) {
		return systemService.getAudittrail(row, page);
	}

	@GetMapping("/audit/page/{row}")
	public ResponseEntity<PaginateValues> getPageValues(@PathVariable int row) {
		return systemService.getPageValues(row);
	}

	@PostMapping("/validate/reset")
	public ResponseEntity<Map<String, Object>> validateReset(Useraccount user) {
		return systemService.validateReset(user);
	}

	@PostMapping("/reset/password")
	public ResponseEntity<Map<String, Object>> resetPassword(Useraccount user) {
		return systemService.resetPassword(user);
	}

}
