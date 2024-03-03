package com.pa.mapper;

import com.pa.dto.AuthorDTO;
import com.pa.entity.Author;

public class AuthorMapper {
	public static Author mapToAuthor(AuthorDTO authorDTO) {
		return new Author(
				authorDTO.getName(),
				authorDTO.getBio()
			);
	}
	
	public static AuthorDTO mapToAuthorDTO(Author author) {
		return new AuthorDTO(
				author.getName(),
				author.getBio()
			);
	}
}
