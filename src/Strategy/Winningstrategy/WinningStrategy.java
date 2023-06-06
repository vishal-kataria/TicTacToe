package Strategy.Winningstrategy;

import Models.*;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);
}
