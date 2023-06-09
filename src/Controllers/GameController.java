package Controllers;

import Models.Game;
import Models.GameStatus;
import Models.Player;
import Models.PlayerType;
import Strategy.Winningstrategy.WinningStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {


    public Game createGame(int n, List<Player> players, List<WinningStrategy> winningStrategies){

        return Game.getBuilder().setDimension(n).setPlayers(players).setWinningStrategies(winningStrategies).build();
    }

    public void displayBoard(Game game){
        game.printBoard();
    }

    public void undo(Game game){
        game.undo();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void printWinner(Game game){
        game.printResult();
    }

    public void printResult(Game game){
//        GameStatus gameStatus = game.getGameStatus();
//
//        if(gameStatus.equals(GameStatus.ENDED)){
//            game.printWinner();
//        }else{
//            System.out.println("Game is DRAW");
//        }
    }

}
