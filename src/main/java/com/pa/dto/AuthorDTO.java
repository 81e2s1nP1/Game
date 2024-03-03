package com.pa.dto;

public class AuthorDTO {
	private String name;
	private String bio;
	
	public AuthorDTO() {
		super();
	}

	public AuthorDTO(String name, String bio) {
		super();
		this.name = name;
		this.bio = bio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}
