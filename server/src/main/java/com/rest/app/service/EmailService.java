/**
 * 
 */
package com.rest.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

/**
 * @author danielf
 *
 */
public interface EmailService {

	ResponseEntity<Map<String, Object>> sendEmail(List<String> email, String message, String subject);

}