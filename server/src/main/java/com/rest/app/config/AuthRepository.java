/**
 * 
 */
package com.rest.app.config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.app.repo.UseraccountRepository;
import com.rest.app.table.Useraccount;

/**
 * @author danielf
 *
 */
@Service("authService")
@Transactional
public class AuthRepository implements UserDetailsService {

	@Autowired
	private UseraccountRepository userService;

	@SuppressWarnings("unused")
	@Autowired
	private AuthAttempRepository attemptService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		try {
			Useraccount user = userService.findByUsername(username);
			if (user != null) {
				return new User(user.getUsername(), user.getPassword(), !user.getDisabled(), true, true, true,
						new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@SuppressWarnings("unused")
	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

}
