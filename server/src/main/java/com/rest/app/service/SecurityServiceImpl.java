/**
 * 
 */
package com.rest.app.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.rest.app.config.AuthAttempRepository;
import com.rest.app.config.AuthRepository;
import com.rest.app.util.EventUtil;
import com.rest.app.util.JWTUtil;
import com.rest.app.util.LoginRequest;
import com.rest.app.util.LoginResponse;

/**
 * @author danielf
 *
 */
@Component
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private AuthRepository authService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthAttempRepository attemptService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private EventUtil event;

	public ResponseEntity<LoginResponse> authenticateUser(LoginRequest requestcredentials) {
		try {

			String ip = getClientIP();

			if (attemptService.isBlocked(requestcredentials.getUsername()) || attemptService.isBlocked(ip)) {
				return new ResponseEntity<>(new LoginResponse(requestcredentials.getUsername(), "",
						"Temporarily Locked", HttpStatus.LOCKED.getReasonPhrase()), HttpStatus.LOCKED);
			}

			String flag = authenticate(requestcredentials.getUsername(), requestcredentials.getPassword());
			final UserDetails res = authService.loadUserByUsername(requestcredentials.getUsername());

			if (flag != null && flag.equals("Accepted") && res != null) {
				final String token = jwtUtil.generateToken(res);
				LoginResponse response = new LoginResponse(res.getUsername(), token, "Login Success",
						HttpStatus.ACCEPTED.getReasonPhrase());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("event", "System Logged in");
				map.put("flag", "success");
				event.LOG_EVENT(res.getUsername(), map);
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return new ResponseEntity<>(new LoginResponse(requestcredentials.getUsername(), "", "Unauthorized",
				HttpStatus.UNAUTHORIZED.getReasonPhrase()), HttpStatus.UNAUTHORIZED);
	}

	public String authenticate(String username, String password) throws Exception {
		// String ip = getClientIP();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			attemptService.loginSucceeded(username);
			// attemptService.loginSucceeded(ip);
			return "Accepted";
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			attemptService.loginFailed(username);
			// attemptService.loginFailed(ip);
			e.printStackTrace();
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

}
