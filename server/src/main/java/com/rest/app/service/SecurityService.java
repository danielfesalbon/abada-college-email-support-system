/**
 * 
 */
package com.rest.app.service;

import org.springframework.http.ResponseEntity;

import com.rest.app.util.LoginRequest;
import com.rest.app.util.LoginResponse;

/**
 * @author danielf
 *
 */
public interface SecurityService {

	ResponseEntity<LoginResponse> authenticateUser(LoginRequest requestcredentials);

	String authenticate(String username, String password) throws Exception;

	String getClientIP();

}
