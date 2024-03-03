package com.pa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pa.dto.AuthorDTO;
import com.pa.dto.GameDTO;
import com.pa.dto.TypeDTO;
import com.pa.entity.Game;
import com.pa.request.UpdateGameRequest;
import com.pa.service.GameService;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/game")
public class GameController {
	@Autowired
    private GameService gameService;
	
	//API CREATE A NEW GAME
	@PostMapping(value = "/create")
    public ResponseEntity<Game> createGame(@RequestPart("gameDTO") GameDTO gameDTO,
    		@RequestPart("authorDTO") AuthorDTO authorDTO,
    		@RequestPart("typeDTO") TypeDTO typeDTO,
    		@RequestPart("files") MultipartFile[] files) {
        Game createdGame;
		try {
			createdGame = gameService.create(gameDTO, authorDTO, typeDTO, files);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }
    
  //API DELETE A GAME
    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteGame(@RequestParam("id") Integer id) {
        Boolean deleted = gameService.delete(id);
        if (deleted) {
            return new ResponseEntity<>("Game deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Game not found or cannot be deleted", HttpStatus.NOT_FOUND);
        }
    }
    
    //API UPDATE GAME
    @PutMapping("/update/{id}")
    public ResponseEntity<Game> updateGame(
            @PathVariable("id") Integer id,
            @RequestBody UpdateGameRequest updateGameRequest) {
    	
        Game updatedGame = gameService.update(id, updateGameRequest.getGameDTO(),
        		updateGameRequest.getAuthorDTO(),
        		updateGameRequest.getTypeDTO());
        
        if (updatedGame != null) {
            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

