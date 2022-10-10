package com.mb.api.web.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginModel
{
	@NotBlank(message = "This filed must be required!")
	@Email(message = "Please enter the valid email")
	private String email;
	
	@NotBlank(message = "This filed must be required!")
	private String password;

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}
	
}
