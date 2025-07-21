package org.example.Strategies.botPlayingStrategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.CellState;
import org.example.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move makeMove(Board board) {
        // Iterate over the board and make the move at the first empty cell
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(
                            null,
                            cell
                    );
                }
            }
        }
        return null;
    }
}
