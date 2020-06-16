package com.kodpalmowy.database.dataAccessObject;

import com.kodpalmowy.database.models.Movie;
import com.kodpalmowy.database.utils.ConnectionClass;
import com.kodpalmowy.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao extends Dao {

    private List<Movie> movieList = new ArrayList<>();

    public List<Movie> queryMovies(){
        String SQL_SELECT_QUERY = "SELECT * FROM movieList";
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement= connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_QUERY)){
            while (resultSet.next()){
                Movie movie = new Movie();
                setResultToMovie(movie, resultSet);
                movieList.add(movie);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException(SELECT) : " + sqle.getMessage());
        }
        return movieList;
    }

    public void deleteMovie(Integer id){
        String SQL_DELETE_QUERY = "DELETE FROM movieList WHERE movieId=" + id;
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(SQL_DELETE_QUERY);
        } catch (SQLException sqle){
            System.out.println("SQLException(DELETE) : " + sqle.getMessage());
            // change this later to logger
        }
    }

    public void insertMovie(Movie movie){
        String SQL_INSERT_QUERY = "INSERT INTO movieList (title, director, genre, description, rating, country, releaseDate) VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = ConnectionClass.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)){
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDirector());
            preparedStatement.setString(3, movie.getGenre());
            preparedStatement.setString(4, movie.getDescription());
            preparedStatement.setInt(5, movie.getRating());
            preparedStatement.setString(6, movie.getCountry());
            preparedStatement.setDate(7, java.sql.Date.valueOf(Utils.convertToLocalDate(movie.getReleaseDate())));
        } catch (SQLException sqle) {
            System.out.println("SQLException(INSERT) : " + sqle.getMessage());
            // change this later to logger
        }
    }

    public void updateMovie(Movie movie) {
        String SQL_UPDATE_QUERY = "UPDATE movieList SET title='" + movie.getTitle() + "', director='" + movie.getDirector()
                + "', genre='" + movie.getGenre() + "', description='" + movie.getDescription()
                + "', rating='" + movie.getRating() + "', country='" + movie.getCountry() + "', releaseDate='" + Utils.convertToLocalDate(movie.getReleaseDate())
                + "' WHERE movieId=" + movie.getMovieId();
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_UPDATE_QUERY);
        } catch (SQLException sqle) {
            System.out.println("SQLException(UPDATE) : " + sqle.getMessage());
            // change this later to logger
        }
    }

    private void setResultToMovie(Movie movie, ResultSet resultSet) {
        try {
            movie.setMovieId(resultSet.getInt("gameId"));
            movie.setTitle(resultSet.getString("title"));
            movie.setDirector(resultSet.getString("director"));
            movie.setGenre(resultSet.getString("genre"));
            movie.setDescription(resultSet.getString("description"));
            movie.setRating(resultSet.getInt("rating"));
            movie.setCountry(resultSet.getString("country"));
            movie.setReleaseDate(Utils.convertToDate(resultSet.getDate("releaseDate").toLocalDate()));
        } catch (SQLException sqle) {
            System.out.println("SQLException(SET RESULT TO MOVIE) : " + sqle.getMessage());
        }
    }

}
