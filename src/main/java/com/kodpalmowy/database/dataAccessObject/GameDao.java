package com.kodpalmowy.database.dataAccessObject;

import com.kodpalmowy.database.models.Game;
import com.kodpalmowy.database.utils.ConnectionClass;
import com.kodpalmowy.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao extends Dao{

    private List<Game> gameList = new ArrayList<>();

    public void insertGame(Game game){
        String SQL_INSERT_QUERY = "INSERT INTO gameList (title, publisher, genre, description, platform, mode, rating, releaseDate) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = ConnectionClass.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)) {
            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, game.getPublisher());
            preparedStatement.setString(3, game.getGenre());
            preparedStatement.setString(4, game.getDescription());
            preparedStatement.setString(5, game.getPlatform());
            preparedStatement.setString(6, game.getMode());
            preparedStatement.setInt(7, game.getRating());
            preparedStatement.setDate(8, java.sql.Date.valueOf(Utils.convertToLocalDate(game.getReleaseDate())));
            preparedStatement.execute();
        } catch (SQLException sqle) {
            System.out.println("SQLException(INSERT) : " + sqle.getMessage());
            // change this later to logger
        }
    }

    public List<Game> queryGames(){
        String SQL_SELECT_QUERY = "SELECT * FROM gameList";
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_QUERY)) {
            while (resultSet.next()) {
                Game game = new Game();
                setResultToGame(game, resultSet);
                gameList.add(game);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException(SELECT) : " + sqle.getMessage());
        }
        return gameList;
    }

    public void deleteGame(Integer id) {
        String SQL_DELETE_QUERY = "DELETE FROM gameList WHERE gameId=" + id;
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_DELETE_QUERY);
        } catch (SQLException sqle) {
            System.out.println("SQLException(DELETE) : " + sqle.getMessage());
            // change this later to logger
        }
    }

    public void updateGame(Game game) {
        String SQL_UPDATE_QUERY = "UPDATE gameList SET title='" + game.getTitle() + "', publisher='" + game.getPublisher()
                + "', genre='" + game.getGenre() + "', description='" + game.getDescription() + "', platform='" + game.getPlatform()
                + "', mode='" + game.getMode() + "', rating='" + game.getRating() + "', releaseDate='" + Utils.convertToLocalDate(game.getReleaseDate())
                + "' WHERE gameId=" + game.getGameId();
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_UPDATE_QUERY);
        } catch (SQLException sqle) {
            System.out.println("SQLException(UPDATE) : " + sqle.getMessage());
            // change this later to logger
        }
    }

    private void setResultToGame(Game game, ResultSet resultSet) {
        try {
            game.setGameId(resultSet.getInt("gameId"));
            game.setTitle(resultSet.getString("title"));
            game.setPublisher(resultSet.getString("publisher"));
            game.setGenre(resultSet.getString("genre"));
            game.setDescription(resultSet.getString("description"));
            game.setPlatform(resultSet.getString("platform"));
            game.setMode(resultSet.getString("mode"));
            game.setRating(resultSet.getInt("rating"));
            game.setReleaseDate(Utils.convertToDate(resultSet.getDate("releaseDate").toLocalDate()));
        } catch (SQLException sqle) {
            System.out.println("SQLException(SET RESULT TO GAME) : " + sqle.getMessage());
        }
    }
}
