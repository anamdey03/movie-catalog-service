package com.practice.moviecatalogservice.entity;

public class Ratings {

	private int id;
	private String movieId;
	private int rating;
	private String userId;

	public Ratings() {

	}

	public Ratings(int id, String movieId, int rating, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.rating = rating;
		this.userId = userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
