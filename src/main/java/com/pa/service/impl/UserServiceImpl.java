package com.pa.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pa.dto.UserDTO;
import com.pa.dto.UserDataLoginDTO;
import com.pa.entity.Role;
import com.pa.entity.User;
import com.pa.entity.UserDataLogin;
import com.pa.exception.CrackGameAPIException;
import com.pa.repository.UserLoginDataRepo;
import com.pa.repository.UserRepo;
import com.pa.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserLoginDataRepo userLoginDataRepo;
	@Autowired
	private UserRepo userRepo;
	
	//CREATE NEW USER
	@Override
	public User create(UserDTO uDto, UserDataLoginDTO dataLoginDTO) {
		if(userLoginDataRepo.findByEmail(dataLoginDTO.getEmail()) != null) {
			throw new CrackGameAPIException(HttpStatus.BAD_REQUEST, "Email Exist");
		}else {
			// set Roles
			Set<Role> roles = new HashSet<>();
			Role role = new Role("ROLE_USER");
			roles.add(role);
			
			//map UserDataLoginDTO to UserDataLogin
			UserDataLogin userDataLogin = 
					new UserDataLogin(dataLoginDTO.getEmail(), dataLoginDTO.getPassword());
			
			//create User
			User u = new User(uDto.getUserName(),
					uDto.getNumberPhone(),
					userDataLogin,
					roles);
			return userRepo.save(u);
		}
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}
	
	@Override
	public User getById(int id) {
	    Optional<User> optionalUser = userRepo.findById(id);
	    if (optionalUser.isPresent()) {
	        return optionalUser.get();
	    } else {
	        return null;
	    }
	}

	
	@Override
	public User update(int id, UserDTO uDto, UserDataLoginDTO dataLoginDTO) {
	    // Check if User exists
	    User existingUser = this.getById(id);
	    if (existingUser != null && uDto != null) {
	        // Update UserDTO information to User
	        existingUser.setUserName(uDto.getUserName());
	        existingUser.setNumberPhone(uDto.getNumberPhone());
	        
	        // Update UserDataLoginDTO information to UserDataLogin
	        UserDataLogin userDataLogin = existingUser.getUserDataLogin();
	        if (userDataLogin != null && dataLoginDTO != null) {
	            userDataLogin.setEmail(dataLoginDTO.getEmail());
	            userDataLogin.setPassword(dataLoginDTO.getPassword());
	        } else {
	        	return null;
	        }
	        
	        // Save the updated User to the database
	        return userRepo.save(existingUser);
	    }
	    return null;
	}


	//remove user func
	@Override
	public boolean delete(int id) {
	    // Check if the User exists
	    User existingUser = this.getById(id);
	    if (existingUser != null) {
	        // Delete the User from the database
	        userRepo.delete(existingUser);
	        return true;
	    }
	    return false;
	}
}
