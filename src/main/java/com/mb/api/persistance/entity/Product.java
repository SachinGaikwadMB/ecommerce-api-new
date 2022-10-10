package com.mb.api.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Product extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "product_code", unique = true)
	private String code;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "product_brand")
	private String brand;
	
	@Column(name = "product_category")
	private String category;
	
	@Column(name = "product_price")
	private Double price;
	
	@Column(name = "stock_units")
	private Integer stockUnits;
	
}
