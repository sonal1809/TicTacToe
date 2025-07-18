package org.example.Strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Move;

public class ColumnWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
