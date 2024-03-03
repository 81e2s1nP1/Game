package com.pa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pa.entity.Game;

public interface GameRepo extends JpaRepository<Game, Integer>{
	public Game findByName(String name);
	
	@Query("SELECT g FROM Game g WHERE g.author.id = ?1")
	public List<Game> findByAuthorId(Integer authorId);

    @Query("SELECT g FROM Game g JOIN g.types t WHERE t.id = ?1")
    public List<Game> findByTypeId(Integer typeId);
    
    @Query("SELECT g FROM Game g WHERE g.name LIKE CONCAT('%', :keyword, '%') ")
    public List<Game> findGameByChar(@Param("keyword") String keyword);
    
    @Query("SELECT g FROM Game g JOIN g.types t WHERE t.typeName = ?1")
    public List<Game> findGameByType(String typeName);
}
