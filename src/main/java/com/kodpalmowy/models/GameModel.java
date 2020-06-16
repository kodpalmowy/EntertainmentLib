package com.kodpalmowy.models;

import com.kodpalmowy.database.dataAccessObject.GameDao;
import com.kodpalmowy.database.models.Game;
import com.kodpalmowy.utils.converters.GameConverter;

public class GameModel {

    public Game saveGameInDB(GameFx gameFx){
        Game game = GameConverter.convertToGame(gameFx);
        GameDao gameDao = new GameDao();
        gameDao.insertGame(game);
        return game;
    }
    public void updateGameInDB(GameFx gameFx){
        Game game = GameConverter.convertToGame(gameFx);
        GameDao gameDao = new GameDao();
        gameDao.updateGame(game);
    }
}
