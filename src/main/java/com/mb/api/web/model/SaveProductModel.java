package com.mb.api.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SaveProductModel
{
	@NotBlank(message = "This field must be required !")
	@Size(max = 50, message = "Size exceeds than 50 characters")
	private String name;
	
	@NotBlank(message = "This field must be required !")
	@Size(max = 50, message = "Size exceeds than 50 characters")
	private String brand;
	
	@NotBlank(message = "This field must be required !")
	@Size(max = 50, message = "Size exceeds than 50 characters")
	private String category;
	
	@NotBlank(message = "This field must be required !")
	@Size(max = 50, message = "Size exceeds than 50 characters")
	private String code;
	
	private Double price;
	
	private Integer stockUnits;

	public String getName()
	{
		return name;
	}

	public String getBrand()
	{
		return brand;
	}

	public String getCategory()
	{
		return category;
	}

	public Double getPrice()
	{
		return price;
	}
	
	public String getCode()
	{
		return code;
	}

	public Integer getStockUnits()
	{
		return stockUnits;
	}


}
