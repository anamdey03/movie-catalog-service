package com.practice.moviecatalogservice.entity;

import java.util.List;

public class UserRatings {

	private List<UserRating> userRating;

	public UserRatings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRatings(List<UserRating> userRating) {
		super();
		this.userRating = userRating;
	}

	public List<UserRating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<UserRating> userRating) {
		this.userRating = userRating;
	}

}
