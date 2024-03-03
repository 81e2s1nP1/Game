package com.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pa.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer>{
	public Author findByName(String name);
}
