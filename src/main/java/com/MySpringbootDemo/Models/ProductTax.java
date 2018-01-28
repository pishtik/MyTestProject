package com.MySpringbootDemo.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producttax")
public class ProductTax {
	
	@Column
	private Integer TaxId;
	@Column
	private String TaxName;
	@Column
	private String TaxDescription;
	private Product product;
	
	public ProductTax() {	
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return TaxId;
	}
	@Column(name = "TaxId")
	public Integer getTaxId() {
		return TaxId;
	}

	public void setTaxId(Integer taxId) {
		TaxId = taxId;
	}
	@Column(name = "TaxName")
	public String getTaxName() {
		return TaxName;
	}

	public void setTaxName(String taxName) {
		TaxName = taxName;
	}
	@Column(name = "TaxDescription")
	public String getTaxDescription() {
		return TaxDescription;
	}

	public void setTaxDescription(String taxDescription) {
		TaxDescription = taxDescription;
	}

	@OneToOne(mappedBy = "productTax")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
