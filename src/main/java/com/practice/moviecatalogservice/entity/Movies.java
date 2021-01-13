package com.practice.moviecatalogservice.entity;

public class Movies {

	private long movieId;
	private String title;
	private String description;

	public Movies() {
		
	}

	public Movies(long movieId, String title, String description) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.description = description;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", title=" + title + ", description=" + description + "]";
	}

}
