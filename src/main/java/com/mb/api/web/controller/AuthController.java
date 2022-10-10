package com.mb.api.web.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.services.UserService;
import com.mb.api.web.model.LoginModel;
import com.mb.api.web.model.RegisterModel;

import static com.mb.api.constants.GenericConstanst.AUTH_URL;
import static com.mb.api.constants.GenericConstanst.SIGNUP_USR;
import static com.mb.api.constants.GenericConstanst.SIGNUP_ADMIN;
import static com.mb.api.constants.GenericConstanst.LOGIN;


@RestController
@RequestMapping(AUTH_URL)
public class AuthController extends BaseController
{

	@Autowired
	private UserService userService;
	
	
	@PostMapping(SIGNUP_USR)
	public ResponseEntity<Object> registerUser(@RequestBody @Valid RegisterModel registerModel)
	{
		Object responseMsg = userService.registerUser(registerModel);
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}
	
	@PostMapping(SIGNUP_ADMIN)
	public ResponseEntity<Object> registerAdmin(@RequestBody @Valid RegisterModel registerModel)
	{
		Object responseMsg = userService.registerAdmin(registerModel);
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}
	
	@PostMapping(LOGIN)
	public ResponseEntity<Object> login(@RequestBody @Valid LoginModel loginModel) {
		return new ResponseEntity<>(userService.authenticate(loginModel), HttpStatus.OK);
	}
}
