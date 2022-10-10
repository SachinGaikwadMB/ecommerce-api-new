package com.mb.api.web.model;

import javax.validation.constraints.NotBlank;

public class AddToCartModel
{
	@NotBlank
	private String code;

	private Integer userId;
	
	private Integer quantity;

	public Integer getQuantity()
	{
		return quantity;
	}

	public String getCode()
	{
		return code;
	}

	public Integer getUserId()
	{
		return userId;
	}

}
