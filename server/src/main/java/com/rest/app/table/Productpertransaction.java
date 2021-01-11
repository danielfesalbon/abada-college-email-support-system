/**
 * 
 */
package com.rest.app.table;

import java.math.BigDecimal;

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
@Table(name = "PRODUCTPERTRANSACTION")
public class Productpertransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "productid")
	private String productid;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "transactionid")
	private String transactionid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	@Override
	public String toString() {
		return "Productpertransaction [id=" + id + ", productid=" + productid + ", amount=" + amount + ", quantity="
				+ quantity + ", transactionid=" + transactionid + "]";
	}

}
