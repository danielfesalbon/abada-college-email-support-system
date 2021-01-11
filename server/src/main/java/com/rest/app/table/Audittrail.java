/**
 * 
 */
package com.rest.app.table;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author danielf
 *
 */
@Entity
@Table(name = "AUDITTRAIL")
public class Audittrail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "reference")
	private String reference;
	@Column(name = "username")
	private String username;
	@Column(name = "eventdesc")
	private String eventdesc;
	@Column(name = "eventdate")
	private Date eventdate;
	@Column(name = "eventtime")
	private Time eventtime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEventdesc() {
		return eventdesc;
	}

	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public Time getEventtime() {
		return eventtime;
	}

	public void setEventtime(Time eventtime) {
		this.eventtime = eventtime;
	}

}
