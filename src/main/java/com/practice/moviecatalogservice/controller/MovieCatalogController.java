package com.practice.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.practice.moviecatalogservice.entity.MovieCatalog;
import com.practice.moviecatalogservice.entity.MovieCatalogs;
import com.practice.moviecatalogservice.entity.Movies;
import com.practice.moviecatalogservice.entity.Ratings;
import com.practice.moviecatalogservice.entity.UserRating;
import com.practice.moviecatalogservice.entity.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<MovieCatalog>> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId,
				UserRating.class);

		return new ResponseEntity<List<MovieCatalog>>(userRating.getRatings().stream().map(rating -> {
			Movies movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
					Movies.class);
			return new MovieCatalog(movie.getTitle(), movie.getDescription(), rating.getRating());
		}).collect(Collectors.toList()), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MovieCatalogs>> getCatalogsForAllUsers() {
		UserRatings userRatings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/",
				UserRatings.class);
		
		List<MovieCatalogs> movieCats = new ArrayList<MovieCatalogs>();
		for (UserRating userRating : userRatings.getUserRating()) {
			MovieCatalogs movieCat = new MovieCatalogs();
			List<MovieCatalog> movieCatalogs = new ArrayList<MovieCatalog>();
			for (Ratings ratings : userRating.getRatings()) {
				MovieCatalog movieCatalog = new MovieCatalog();
				Movies movie = restTemplate.getForObject("http://movie-info-service/movies/" + ratings.getMovieId(),
						Movies.class);
				movieCatalog.setName(movie.getTitle());
				movieCatalog.setDescription(movie.getDescription());
				movieCatalog.setRating(ratings.getRating());
				movieCatalogs.add(movieCatalog);
			}
			movieCat.setUserId(userRating.getUserId());
			movieCat.setMovieCatalog(movieCatalogs);
			movieCats.add(movieCat);
		}
		return new ResponseEntity<List<MovieCatalogs>>(movieCats, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public ResponseEntity<String> addMovieRatingByUser(@PathVariable("userId") String userId, @RequestBody MovieCatalog movieCatalog){
		Movies movies = new Movies();
		movies.setTitle(movieCatalog.getName());
		movies.setDescription(movieCatalog.getDescription());
		String movieId = restTemplate.postForObject("http://movie-info-service/movies", movies, String.class);
		Ratings ratings = new Ratings();
		ratings.setMovieId(movieId);
		ratings.setRating(movieCatalog.getRating());
		String response = restTemplate.postForObject("http://ratings-data-service/ratingsdata/user/" + userId, ratings, String.class, userId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateRatingForMovie(@PathVariable("userId") String userId, @RequestBody MovieCatalog movieCatalog) {
		String movieId = restTemplate.getForObject("http://movie-info-service/movies/title/" + movieCatalog.getName(), String.class);
		Ratings ratings = new Ratings();
		ratings.setMovieId(movieId);
		ratings.setRating(movieCatalog.getRating());
		restTemplate.put("http://ratings-data-service/ratingsdata/user/" + userId, ratings, userId);
		return new ResponseEntity<String>("Ratings updated successfully for Movie " + movieCatalog.getName(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/{movieName}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRatingForMovie(@PathVariable("userId") String userId, @PathVariable("movieName") String movieName) {
		String movieId = restTemplate.getForObject("http://movie-info-service/movies/title/" + movieName, String.class);
		restTemplate.delete("http://ratings-data-service/ratingsdata/user/"+ userId +"/"+ movieId);
		return new ResponseEntity<String>("Ratings Deleted successfully for Movie " + movieName, HttpStatus.OK);
	}
}
