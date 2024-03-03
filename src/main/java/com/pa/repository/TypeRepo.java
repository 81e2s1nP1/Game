package com.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pa.entity.Type;

public interface TypeRepo extends JpaRepository<Type, Integer> {
	public Type findByTypeName(String typeName);
}
