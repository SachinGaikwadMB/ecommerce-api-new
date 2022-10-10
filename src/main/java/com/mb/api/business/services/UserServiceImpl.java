package com.mb.api.business.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mb.api.business.exceptions.CustomException;
import com.mb.api.business.exceptions.ResourceAlreadyExistsException;
import com.mb.api.constants.ERole;
import com.mb.api.jwt.JwtUtils;
import com.mb.api.persistance.entity.User;
import com.mb.api.persistance.repository.RoleRepository;
import com.mb.api.persistance.repository.UserRepository;
import com.mb.api.web.model.LoginModel;
import com.mb.api.web.model.RegisterModel;

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Object registerUser(RegisterModel registerModel)
	{
		Optional<User> optUser = userRepository.findByEmail(registerModel.getEmail());

		if (optUser.isPresent())
		{
			throw new ResourceAlreadyExistsException("Error :: User Already Exist with email :: " + registerModel.getEmail());
		}

		try
		{
			logger.info("User is saving...");
			User user = modelMapper.map(registerModel, User.class);
			user.setPassword(passwordEncoder.encode(registerModel.getPassword()));
			user.setRoles(Arrays.asList(roleRepository.findByRoleName(ERole.ROLE_USER)));
			userRepository.save(user);
			logger.info("User saved successfully !");
		}
		catch (Exception ex)
		{
			throw new CustomException("Error :: Interval Server Error !");
		}

		return "Success :: User saved successfully ! ";
	}

	@Override
	public Object registerAdmin(RegisterModel registerModel)
	{

		Optional<User> optAdmin = userRepository.findByEmail(registerModel.getEmail());

		if (optAdmin.isPresent())
		{
			throw new ResourceAlreadyExistsException("Error :: User Already Exist with email :: " + registerModel.getEmail());
		}

		try
		{
			logger.info("Admin is saving...");
			User admin = modelMapper.map(registerModel, User.class);
			admin.setPassword(passwordEncoder.encode(registerModel.getPassword()));
			admin.setRoles(Arrays.asList(roleRepository.findByRoleName(ERole.ROLE_ADMIN)));
			userRepository.save(admin);
			logger.info("Admin saved successfully !");
		}
		catch (Exception ex)
		{
			throw new CustomException("Error :: Interval Server Error !");
		}

		return "Success :: Admin saved successfully ! ";
	}

	@Override
	public Object authenticate(LoginModel loginModel)
	{
		Map<String, Object> data = new HashMap<>();
		
      	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		org.springframework.security.core.userdetails.User secuiryUser = (org.springframework.security.core.userdetails.User ) authentication.getPrincipal();
      	
		List<String> roles = secuiryUser.getAuthorities().stream().map((authority)->authority.getAuthority()).collect(Collectors.toList());
      	
		String jwtToken = jwtUtils.generateJwtToken(loginModel.getEmail());
		
		data.put("email", secuiryUser.getUsername());
		data.put("roles", roles);
		data.put("jwtToken", jwtToken);
		data.put("message", "Login Successful !");
		
		return data;
	}

}
