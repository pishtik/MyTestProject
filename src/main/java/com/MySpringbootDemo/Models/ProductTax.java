package com.MySpringbootDemo.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "producttax")
public class ProductTax {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "taxid")
	private Integer taxid;
	@Column
	private String taxname;
	@Column
	private String taxdescription;
	@Column(name= "metaactive")
	private Integer metaActive;
	

	@JsonIgnore
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productTax")
	private List<Product> products;
	
	public ProductTax(Integer taxid, String taxname, String taxdescription, List<Product> products, Integer metaActive) {
		super();
		this.taxid = taxid;
		this.taxname = taxname;
		this.taxdescription = taxdescription;
		this.products = products;
		this.metaActive = metaActive;
	}

	public ProductTax() {
	}

	public Integer getId() {
		return this.taxid;
	}
	
	public Integer getmetaActive() {
		return metaActive;
	}

	public void setmetaActive(Integer metaActive) {
		this.metaActive = metaActive;
	}

	public void settaxid(Integer taxid) {
		this.taxid = taxid;
	}

	public String gettaxname() {
		return this.taxname;
	}

	public void settaxname(String taxname) {
		this.taxname = taxname;
	}

	public String gettaxdescription() {
		return this.taxdescription;
	}

	public void settaxdescription(String taxdescription) {
		this.taxdescription = taxdescription;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer gettaxid() {
		return taxid;
	}

}
