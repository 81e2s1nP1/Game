package com.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pa.entity.UserDataLogin;

public interface UserLoginDataRepo extends JpaRepository<UserDataLogin, Integer> {
		UserDataLogin findByEmail(String email);
}
