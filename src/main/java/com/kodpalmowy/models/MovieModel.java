package com.kodpalmowy.models;

import com.kodpalmowy.database.dataAccessObject.MovieDao;
import com.kodpalmowy.database.models.Movie;
import com.kodpalmowy.utils.converters.MovieConverter;

public class MovieModel {

    public Movie saveMovieInDB(MovieFx movieFx){
        Movie movie = MovieConverter.convertToMovie(movieFx);
        MovieDao movieDao = new MovieDao();
        movieDao.insertMovie(movie);
        return movie;
    }
    public void updateMovieInDB(MovieFx movieFx){
        Movie movie = MovieConverter.convertToMovie(movieFx);
        MovieDao movieDao = new MovieDao();
        movieDao.updateMovie(movie);
    }
}
