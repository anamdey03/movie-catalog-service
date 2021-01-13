package com.practice.moviecatalogservice.entity;

import java.util.List;

public class UserRating {

	private String userId;
	private List<Ratings> ratings;
	
	public UserRating() {
		
	}

	public UserRating(String userId, List<Ratings> ratings) {
		super();
		this.userId = userId;
		this.ratings = ratings;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}
	
}
