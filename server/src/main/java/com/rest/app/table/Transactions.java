/**
 * 
 */
package com.rest.app.table;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author danielf
 *
 */
@Entity
@Table(name = "TRANSACTIONS")
public class Transactions {

	@Id
	@Column(name = "transactionid")
	private String transactionid;
	@Column(name = "transactiondate")
	private Date transactiondate;
	@Column(name = "transactiontime")
	private Time transactiontime;
	@Column(name = "transactionvalue")
	private BigDecimal transactionvalue;
	@Column(name = "transactionpayment")
	private BigDecimal transactionpayment;
	@Column(name = "transactionchange")
	private BigDecimal transactionchange;
	@Column(name = "transactionreference")
	private String transactionreference;
	@Column(name = "transactiontype")
	private String transactiontype;
	@Column(name = "name")
	private String name;
	@Column(name = "contact")
	private String contact;
	@Column(name = "address")
	private String address;
	@Column(name = "transactionstatus")
	private String transactionstatus;
	@Column(name = "username")
	private String username;

	@Override
	public String toString() {
		return "Transactions [transactionid=" + transactionid + ", transactiondate=" + transactiondate
				+ ", transactiontime=" + transactiontime + ", transactionvalue=" + transactionvalue
				+ ", transactionpayment=" + transactionpayment + ", transactionchange=" + transactionchange
				+ ", transactionreference=" + transactionreference + ", transactiontype=" + transactiontype + ", name="
				+ name + ", contact=" + contact + ", address=" + address + ", transactionstatus=" + transactionstatus
				+ ", username=" + username + "]";
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public Date getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}

	public Time getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(Time transactiontime) {
		this.transactiontime = transactiontime;
	}

	public BigDecimal getTransactionvalue() {
		return transactionvalue;
	}

	public void setTransactionvalue(BigDecimal transactionvalue) {
		this.transactionvalue = transactionvalue;
	}

	public BigDecimal getTransactionpayment() {
		return transactionpayment;
	}

	public void setTransactionpayment(BigDecimal transactionpayment) {
		this.transactionpayment = transactionpayment;
	}

	public BigDecimal getTransactionchange() {
		return transactionchange;
	}

	public void setTransactionchange(BigDecimal transactionchange) {
		this.transactionchange = transactionchange;
	}

	public String getTransactionreference() {
		return transactionreference;
	}

	public void setTransactionreference(String transactionreference) {
		this.transactionreference = transactionreference;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTransactionstatus() {
		return transactionstatus;
	}

	public void setTransactionstatus(String transactionstatus) {
		this.transactionstatus = transactionstatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
