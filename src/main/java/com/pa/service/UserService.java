package com.pa.service;

import java.util.List;

import com.pa.dto.UserDTO;
import com.pa.dto.UserDataLoginDTO;
import com.pa.entity.User;

public interface UserService {
	public List<User> getAll();
	public User getById(int id);
	public User create(UserDTO uDto, UserDataLoginDTO dataLoginDTO);
	public User update(int id, UserDTO uDto, UserDataLoginDTO dataLoginDTO);
	public boolean delete(int id);
}
