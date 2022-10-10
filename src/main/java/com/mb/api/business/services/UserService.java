package com.mb.api.business.services;

import com.mb.api.web.model.LoginModel;
import com.mb.api.web.model.RegisterModel;

public interface UserService
{
	Object registerUser(RegisterModel registerModel);
	
	Object registerAdmin(RegisterModel registerModel);
	
	Object authenticate(LoginModel loginModel);
	
}
