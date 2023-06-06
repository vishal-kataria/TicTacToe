package Strategy.Winningstrategy;

import Models.Board;
import Models.Move;

public class OrderOneDiagonalWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
