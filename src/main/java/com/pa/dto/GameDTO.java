package com.pa.dto;


public class GameDTO {
    private String name;
    private String url;
    private Float size;
    private String description;
    private Integer downloads;

    public GameDTO() {}

    public GameDTO(String name, String url, Float size, String description, Integer downloads) {
        this.name = name;
        this.url = url;
        this.size = size;
        this.description = description;
        this.downloads = downloads;
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
		this.downloads = downloads;
	}
}

