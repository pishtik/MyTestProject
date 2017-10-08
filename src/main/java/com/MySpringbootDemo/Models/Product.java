package com.MySpringbootDemo.Models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String type;
	@Column
	private float price;
	@Column
	private int tax;
	@Column
	private int Meta_Active;
	@Column
	private Date Meta_Modified;
	@Column
	private Date Meta_Created;
	
	public Date getMeta_Created() {
		return Meta_Created;
	}



	public void setMeta_Created(Date meta_Created) {
		Meta_Created = meta_Created;
	}



	public Product() {};


	
	public Product(Long id, String name, String description, String type, float price, int tax, int meta_Active,
			Date meta_Modified) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
		this.tax = tax;
		Meta_Active = meta_Active;
		Meta_Modified = meta_Modified;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getMeta_Active() {
		return Meta_Active;
	}

	public void setMeta_Active(int meta_Active) {
		Meta_Active = meta_Active;
	}

	public Date getMeta_Modified() {
		return Meta_Modified;
	}

	public void setMeta_Modified(Date meta_Modified) {
		Meta_Modified = meta_Modified;
	}

	@Override
	public String toString() {
		return String.format(
				"Product [id=%s, name=%s, description=%s, type=%s, price=%s, tax=%s, Meta_Active=%s, Meta_Modified=%s]",
				id, name, description, type, price, tax, Meta_Active, Meta_Modified);
	}



	public Long getProductId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	
	
}
