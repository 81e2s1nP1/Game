package com.pa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "game")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String url;
	@Min(value = 1, message = "Size must be greater than or equal to 1")
	private Float size;
	private String description;
//	@PastOrPresent
//	private Date releaseDate; 
	@Min(value = 0, message = "Size must be greater than or equal to 1")
	private Integer downloads;
	
	@ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
	
	@ManyToMany
    @JoinTable(
        name = "game_type",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<Type> types = new HashSet<>();
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GameImg> gameImgs = new ArrayList<>();


	public Game() {
		super();
	}

	public Game(String name, String url, Float size, String description, Integer downloads, Author author, Set<Type> types, List<GameImg> gameImgs) {
		super();
		this.name = name;
		this.url = url;
		this.size = size;
		this.description = description;
//		this.releaseDate = releaseDate;
		this.downloads = (downloads == null) ? 0 : downloads;
		this.author = author;
		this.types = types;
		this.gameImgs = gameImgs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Date getReleaseDate() {
//		return releaseDate;
//	}
//
//	public void setReleaseDate(Date releaseDate) {
//		this.releaseDate = releaseDate;
//	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = (downloads == null) ? 0 : downloads;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Set<Type> getTypes() {
		return types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	public List<GameImg> getGameImgs() {
		return gameImgs;
	}

	public void setGameImgs(List<GameImg> gameImgs) {
		this.gameImgs = gameImgs;
	}
	
}
