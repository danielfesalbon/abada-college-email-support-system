/**
 * 
 */
package com.rest.app.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author danielf
 *
 */
public class SystemProperties {

	@Value("${spring.mail.username}")
	private String email;
	@Value("${spring.mail.port}")
	private String mailport;
	@Value("${spring.mail.host}")
	private String mailhost;
	@Value("${spring.datasource.username}")
	private String dbuser;
	@Value("${spring.datasource.url}")
	private String dburl;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailport() {
		return mailport;
	}

	public void setMailport(String mailport) {
		this.mailport = mailport;
	}

	public String getMailhost() {
		return mailhost;
	}

	public void setMailhost(String mailhost) {
		this.mailhost = mailhost;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDburl() {
		return dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}

}
