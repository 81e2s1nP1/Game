package com.pa.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pa.dto.AuthorDTO;
import com.pa.dto.GameDTO;
import com.pa.dto.TypeDTO;
import com.pa.entity.Author;
import com.pa.entity.Game;
import com.pa.entity.Type;
import com.pa.exception.CrackGameAPIException;
import com.pa.repository.AuthorRepo;
import com.pa.repository.GameRepo;
import com.pa.repository.TypeRepo;
import com.pa.service.GameImgService;
import com.pa.service.GameService;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	private GameRepo gameRepo;
	@Autowired
	private AuthorRepo authorRepo;
	@Autowired
	private TypeRepo typeRepo;
	@Autowired
	private GameImgService gameImgService;
	
	//CREATE THE NEW GAME
	@Override
	public Game create(GameDTO gameDTO, AuthorDTO authorDTO,
			TypeDTO typeDTO, MultipartFile[] folder) throws IOException {
	    // Check if a game with the same name already exists
	    Game existingGame = gameRepo.findByName(gameDTO.getName());
	    if (existingGame != null) {
	        // If the game already exists, check if it has the same author and type
	        if (existingGame.getAuthor().getName().equals(authorDTO.getName()) &&
	                existingGame.getTypes().stream().anyMatch(type -> type.getTypeName().equals(typeDTO.getTypeName()))) {
	            // If it has the same author and type, throw an exception with corresponding error message
	            throw new CrackGameAPIException(HttpStatus.BAD_REQUEST,
	            		"Game with this name already exists with the same author and type");
	        }
	    }

	    // Continue creating or updating the author information
	    Author author = authorRepo.findByName(authorDTO.getName());
	    if (author == null) {
	        author = new Author(authorDTO.getName(), authorDTO.getBio());
	        author = authorRepo.save(author);
	    }

	    // Continue creating or updating the type information
	    Type type = typeRepo.findByTypeName(typeDTO.getTypeName());
	    if (type == null) {
	        type = new Type(typeDTO.getTypeName());
	        type = typeRepo.save(type);
	    }

	    // Create or update the game information
	    Game newGame = new Game(
	            gameDTO.getName(),
	            gameDTO.getUrl(),
	            gameDTO.getSize(),
	            gameDTO.getDescription(),
	            gameDTO.getDownloads(),
	            author,
	            new HashSet<>(Collections.singletonList(type)),
	            gameImgService.uploadImage(folder)
	            );
	    // Save the game to the database
	    return gameRepo.save(newGame);
	}

	@Override
	public List<Game> getLimitedGames(int page) {
	    Pageable pageable = (Pageable) PageRequest.of(page, 12);
	    Page<Game> gamePage = gameRepo.findAll(pageable);
	    return gamePage.getContent();
	}



	@Override
	public Game getById(Integer id) {
		Optional<Game> optionalGame = gameRepo.findById(id);
	    if (optionalGame.isPresent()) {
	        return optionalGame.get();
	    } else {
	        return null;
	    }
	}
	
	//UPDATE GAME
	@Override
	public Game update(Integer id, GameDTO gameDTO, AuthorDTO authorDTO, TypeDTO typeDTO) {
	    // Check if the game exists in the database
	    Game existingGame = gameRepo.findById(id).orElse(null);
	    if (existingGame == null) {
	        // Return null if the game does not exist
	        return null;
	    }

	    // Update game information
	    existingGame.setName(gameDTO.getName());
	    existingGame.setUrl(gameDTO.getUrl());
	    existingGame.setSize(gameDTO.getSize());
	    existingGame.setDescription(gameDTO.getDescription());

	    // Check and update author
	    Author author = authorRepo.findByName(authorDTO.getName());
	    if (author == null) {
	        // If the author does not exist, create a new one and save it to the database
	        author = new Author(authorDTO.getName(), authorDTO.getBio());
	        author = authorRepo.save(author);
	    }
	    existingGame.setAuthor(author);

	    // Check and update type
	    Type type = typeRepo.findByTypeName(typeDTO.getTypeName());
	    if (type == null) {
	        // If the type does not exist, create a new one and save it to the database
	        type = new Type(typeDTO.getTypeName());
	        type = typeRepo.save(type);
	    }
	    existingGame.setTypes(new HashSet<>(Collections.singletonList(type)));

	    // Save the changes to the database and return the updated game
	    return gameRepo.save(existingGame);
	}


	
	
	//DELETE GAME
	@Override
	public Boolean delete(Integer id) {
	    // Check if the game exists in the database
	    Game game = gameRepo.findById(id).orElse(null);
	    if (game == null) {
	        return false;
	    }

	    // Get the author and types of the game
	    Author author = game.getAuthor();
	    Set<Type> types = game.getTypes();

	    // Check if there are other games with the same author or type
	    List<Game> authorGames = gameRepo.findByAuthorId(author.getId());
	    authorGames.remove(game); // Remove the current game from the list
	    boolean hasOtherAuthorGames = !authorGames.isEmpty();

	    List<Game> typeGames = gameRepo.findByTypeId(types.iterator().next().getId());
	    typeGames.remove(game); // Remove the current game from the list
	    boolean hasOtherTypeGames = !typeGames.isEmpty();

	    // If there are no other games with the same author or type,
	    // proceed to delete the game and update the author and types
	    if (!hasOtherAuthorGames && !hasOtherTypeGames) {
	        gameRepo.delete(game);
	        // Update the author if there are no more games with the same author
	        if (authorGames.isEmpty()) {
	            authorRepo.delete(author);
	        }
	        // Update the types if there are no more games with the same types
	        if (typeGames.isEmpty()) {
	            for (Type type : types) {
	                typeRepo.delete(type);
	            }
	        }
	        return true;
	    } else {
	        // Only delete the game without deleting the author and types
	        gameRepo.delete(game);
	        return true;
	    }
	}

	@Override
    public List<Game> getByChar(String character) {
        return gameRepo.findGameByChar(character);
    }

	@Override
	public List<Game> getGameByType(String type) {
	    return gameRepo.findGameByType(type);
	}

}
