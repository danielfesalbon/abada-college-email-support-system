/**
 * 
 */
package com.rest.app.table;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author danielf
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@Column(name = "productid")
	private String productid;
	@Column(name = "productname")
	private String productname;
	@Column(name = "declaredprice")
	private BigDecimal declaredprice;
	@Column(name = "stock")
	private Integer stock;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "imgpath")
	private String imgpath;
	@Column(name = "lastmax")
	private Integer lastmax;

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public BigDecimal getDeclaredprice() {
		return declaredprice;
	}

	public void setDeclaredprice(BigDecimal declaredprice) {
		this.declaredprice = declaredprice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public Integer getLastmax() {
		return lastmax;
	}

	public void setLastmax(Integer lastmax) {
		this.lastmax = lastmax;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname + ", declaredprice=" + declaredprice
				+ ", stock=" + stock + ", active=" + active + ", imgpath=" + imgpath + ", lastmax=" + lastmax + "]";
	}

}
