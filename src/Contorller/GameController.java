package Contorller;

import Model.Game;
import Model.Player;
import WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        try{
            return Game.builder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
        }catch (Exception e){
            System.out.println("Could not start the Game, something went wrong");
        }
        return null;
    }
}
