package com.MySpringbootDemo.Models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
//	@Column
//	private int tax;
	@Column
	private String product_id;
	@Column
	private int metaActive;
	@Column
	private Date Meta_Modified;
	@Column
	private Date Meta_Created;
	@Transient
	private String image;
	
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "tax",referencedColumnName="taxid")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private ProductTax productTax;

	public Date getMeta_Created() {
		return Meta_Created;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setMeta_Created(Date meta_Created) {
		Meta_Created = meta_Created;
	}

	public Product() {
	};

	
//	public ProductTax getProductTax() {
//		return this.productTax;
//	}
//	
//	public void ProductTax(ProductTax productTax){
//		this.productTax = productTax;
//	}
	

	@Override
	public String toString() {
		return String.format(
				"Product [id=%s, name=%s, description=%s, type=%s, price=%s, tax=%s, product_id=%s, Meta_Active=%s, Meta_Modified=%s]",
				id, name, description, type, price, product_id, metaActive, Meta_Modified, image);
	}

	public Product(Long id, String name, String description, String type, float price, int tax, int metaActive,
			Date meta_Modified, String image, ProductTax productTax) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
//		this.tax = tax;
		this.metaActive = metaActive;
		this.Meta_Modified = meta_Modified;
		this.image = image;
		this.productTax = productTax;
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

//	public int getTax() {
//		return tax;
//	}
//
//	public void setTax(int tax) {
//		this.tax = tax;
//	}

	public int getMetaActive() {
		return metaActive;
	}


	public ProductTax getProductTax() {
		return productTax;
	}

	public void setProductTax(ProductTax productTax) {
		this.productTax = productTax;
	}

	public void setMetaActive(int metaActive) {
		this.metaActive = metaActive;
	}

	public Date getMeta_Modified() {
		return Meta_Modified;
	}

	public void setMeta_Modified(Date meta_Modified) {
		Meta_Modified = meta_Modified;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Long getProductId() {
		// TODO Auto-generated method stub
		return id;
	}

}
