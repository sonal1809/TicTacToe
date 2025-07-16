package org.example.Strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board , Player player , Cell cell);
}
