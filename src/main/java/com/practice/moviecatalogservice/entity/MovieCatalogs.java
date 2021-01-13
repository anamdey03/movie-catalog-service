package com.practice.moviecatalogservice.entity;

import java.util.List;

public class MovieCatalogs {

	private String userId;
	private List<MovieCatalog> movieCatalog;

	public MovieCatalogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieCatalogs(String userId, List<MovieCatalog> movieCatalog) {
		super();
		this.userId = userId;
		this.movieCatalog = movieCatalog;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<MovieCatalog> getMovieCatalog() {
		return movieCatalog;
	}

	public void setMovieCatalog(List<MovieCatalog> movieCatalog) {
		this.movieCatalog = movieCatalog;
	}

}
