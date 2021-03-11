package io.microservice.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.microservice.moviecatalogservice.models.CatalogItem;
import io.microservice.moviecatalogservice.models.MovieInfo;
import io.microservice.moviecatalogservice.models.RatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalog {

	@RequestMapping("/{userId}")
	public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userId) {
		/*
		 * List<CatalogItem> list = new ArrayList<CatalogItem>();
		 * CatalogItem item = new CatalogItem("gaurav1", "transformer", 5); 
		 * list.add(item); 
		 * return list;
		 */
		RestTemplate template = new RestTemplate();
		;
		//1. get the list of movies
		
		//2. get the movie info for each movie
		
		//3. consolidate both data and return
		
		//step 1 - hard code it for now and create list of ratings model
		List<RatingInfo> ratings = Arrays.asList(
				new RatingInfo("gaurav1", "movie01", 5),
				new RatingInfo("gaurav1", "movie02", 7));
		
		//step 2 - 
		
		return ratings.stream().map(rating -> {
			MovieInfo movieInfo = template.getForObject("http://localhost:8082/movies/" + rating.getMoviedId(), MovieInfo.class);
			return new CatalogItem(userId, movieInfo.getMovieDescription(), rating.getRating());
		}).collect(Collectors.toList());
		//return Collections.singletonList(new CatalogItem("gaurav1", "transformer", 5));
		
	}
}
