package Strategy.Winningstrategy;

import Models.*;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);

    public void handleUndo(Board board,Move move);
}
