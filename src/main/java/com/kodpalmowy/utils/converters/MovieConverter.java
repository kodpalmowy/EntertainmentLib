package com.kodpalmowy.utils.converters;

import com.kodpalmowy.database.models.Movie;
import com.kodpalmowy.models.MovieFx;
import com.kodpalmowy.utils.Utils;

public class MovieConverter {
    public static Movie convertToMovie(MovieFx movieFx){
        Movie movie = new Movie();
        movie.setTitle(movieFx.getTitle());
        movie.setDirector(movieFx.getDirector());
        movie.setGenre(movieFx.getGenre());
        movie.setCountry(movieFx.getCountry());
        movie.setDescription(movieFx.getDescription());
        movie.setRating(movieFx.getRating());
        movie.setReleaseDate(Utils.convertToDate(movieFx.getReleaseDate()));
        return movie;
    }

    public static MovieFx convertToMovieFx(Movie movie){
        MovieFx movieFx = new MovieFx();
        movieFx.setTitle(movie.getTitle());
        movieFx.setDirector(movie.getDirector());
        movieFx.setGenre(movie.getGenre());
        movieFx.setDescription(movie.getDescription());
        movieFx.setCountry(movie.getCountry());
        movieFx.setReleaseDate(Utils.convertToLocalDate(movie.getReleaseDate()));
        return movieFx;
    }
}
