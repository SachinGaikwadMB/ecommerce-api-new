package com.mb.api.web.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterModel
{
	@NotBlank(message = "This field must be required!")
	@Size(max = 50, message = "First name cann't exceed than 50 characters")
	private String firstName;
	
	
	@NotBlank(message = "This field must be required!")
	@Size(max = 50, message = "First name cann't exceed than 50 characters")
	private String lastName;
	
	@NotBlank(message = "This field must be required!")
	@Email(message = "Please enter valid email")
	private String email;
	
	@NotBlank(message = "This field must be required!")
	private String password;

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}
	
}
