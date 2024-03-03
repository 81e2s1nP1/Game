package com.pa.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pa.dto.AuthorDTO;
import com.pa.dto.GameDTO;
import com.pa.dto.TypeDTO;
import com.pa.entity.Game;

public interface GameService {
	public Game create(GameDTO gameDTO, AuthorDTO authorDTO, TypeDTO typeDTO, MultipartFile[] folder) throws IOException;
	public List<Game> getLimitedGames(int page);
	public Game getById(Integer id);
	public Game  update(Integer id, GameDTO todoDto, AuthorDTO authorDTO, TypeDTO typeDTO);
	public Boolean delete(Integer id);
	public List<Game> getByChar(String character);
	public List<Game> getGameByType(String type);
}
