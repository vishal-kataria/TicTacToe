package Models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    private Scanner scanner;


    Cell makeMove(Board board){
        System.out.println("Please enter ROW number : ");
        int row = scanner.nextInt();

        System.out.println("Please enter COLUMN number : ");
        int col = scanner.nextInt();
        return new Cell(row,col);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Player(Symbol symbol, String name, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
