package com.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pa.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
