package com.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pa.entity.Game;
import com.pa.entity.User;
import com.pa.request.CreateUserRequest;
import com.pa.service.GameService;
import com.pa.service.UserService;

@RestController
public class PublicController {
	@Autowired
	private UserService userService;
	@Autowired GameService gameService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<User> create(@RequestBody CreateUserRequest request) {
		return new ResponseEntity<User>(userService.create(request.getUserDTO(), request.getUserDataLoginDTO()), HttpStatus.CREATED);
	}
	
	// API TO GET GAME BY TYPE
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Game>> findGamesByTypeName(@PathVariable String type) {
        List<Game> games = gameService.getGameByType(type);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    // API TO GET GAME BY CHAR
    @GetMapping("/char/{character}")
    public ResponseEntity<List<Game>> findGamesByCharName(@PathVariable String character) {
        List<Game> games = gameService.getByChar(character);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
