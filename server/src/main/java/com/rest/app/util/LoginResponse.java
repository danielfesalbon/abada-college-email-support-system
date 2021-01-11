/**
 * 
 */
package com.rest.app.util;

/**
 * @author danielf
 *
 */
public class LoginResponse {

	private String username;
	private String token;
	private String message;
	private String flag;

	public LoginResponse(String username, String token, String message, String flag) {
		super();
		this.username = username;
		this.token = token;
		this.message = message;
		this.flag = flag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
