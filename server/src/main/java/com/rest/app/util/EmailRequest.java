/**
 * 
 */
package com.rest.app.util;

import java.util.List;

/**
 * @author danielf
 *
 */
public class EmailRequest {

	private String subject;
	private String message;
	private List<String> email;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

}
