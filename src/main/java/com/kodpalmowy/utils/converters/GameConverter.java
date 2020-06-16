package com.kodpalmowy.utils.converters;

import com.kodpalmowy.database.models.Game;
import com.kodpalmowy.models.GameFx;
import com.kodpalmowy.utils.Utils;

public class GameConverter {
    public static Game convertToGame(GameFx gameFx){
        Game game = new Game();
        game.setGameId(gameFx.getGameId());
        game.setTitle(gameFx.getTitle());
        game.setPublisher(gameFx.getPublisher());
        game.setGenre(gameFx.getGenre());
        game.setDescription(gameFx.getDescription());
        game.setMode(gameFx.getMode());
        game.setPlatform(gameFx.getPlatform());
        game.setRating(gameFx.getRating());
        game.setReleaseDate(Utils.convertToDate(gameFx.getReleaseDate()));
        return game;
    }

    public static GameFx convertToGameFx(Game game){
        GameFx gameFx = new GameFx();
        gameFx.setGameId(game.getGameId());
        gameFx.setTitle(game.getTitle());
        gameFx.setPublisher(game.getPublisher());
        gameFx.setGenre(game.getGenre());
        gameFx.setDescription(game.getDescription());
        gameFx.setMode(game.getMode());
        gameFx.setPlatform(game.getPlatform());
        gameFx.setRating(game.getRating());
        gameFx.setReleaseDate(Utils.convertToLocalDate(game.getReleaseDate()));
        return gameFx;
    }
}
