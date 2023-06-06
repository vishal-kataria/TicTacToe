import Controllers.GameController;
import Models.*;
import Strategy.Winningstrategy.OrderOneColWinningStrategy;
import Strategy.Winningstrategy.OrderOneDiagonalWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       //creat a game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        Game game;



        try{
            game = gameController.createGame(3,
                    List.of(
                            new Player(new Symbol('X'),"Vishal ",PlayerType.HUMAN),
                            new Bot(new Symbol('O'),"Bot ", BotDifficultLevel.EASY)
                            ),
                    List.of(
                            new OrderOneDiagonalWinningStrategy(),
                            new OrderOneColWinningStrategy(),
                            new OrderOneDiagonalWinningStrategy()
                            )
                    );
        }catch (Exception e){
            System.out.println("something went wrong");
            return;
        }

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            //printing
            gameController.displayBoard(game);
            //for undo
            System.out.println("Do you want to UNDO? (Y?N)");
            String input = scanner.next();

            if (input.equals("y")){
                gameController.undo(game);
            }
            else {
                //next move
                gameController.makeMove(game);
            }
        }
        //getting results

        gameController.printResult(game);

    }
}