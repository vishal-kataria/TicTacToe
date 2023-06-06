import Controllers.GameController;
import Models.*;
import Strategy.Winningstrategy.OrderOneColWinningStrategy;
import Strategy.Winningstrategy.OrderOneDiagonalWinningStrategy;
import Strategy.Winningstrategy.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       //creat a game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        Game game;
        int n = 3;
        List<Player> players = List.of(
                new Player(new Symbol('X'),"Vishal ",PlayerType.HUMAN),
                new Bot(new Symbol('O'),"Bot ", BotDifficultLevel.EASY)
        );

        try{
            game = gameController.createGame(n,players,
                    List.of(
                            new OrderOneDiagonalWinningStrategy(n,players),
                            new OrderOneColWinningStrategy(n,players),
                            new OrderOneRowWinningStrategy(n,players)
                            )
                    );
        }catch (Exception e){
            System.out.println("something went wrong");
            return;
        }
        System.out.println("----------------Game is starting----------------");
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println("Currently board looks like");
            gameController.displayBoard(game);
            System.out.println("Do anyone want to UNDO? (Y?N)");
            String input = scanner.next();
            if (input.equals("y")){
                gameController.undo(game);
            }
            else {
                gameController.makeMove(game);
            }
        }
        gameController.printWinner(game);

    }
}