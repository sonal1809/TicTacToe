package org.example.Strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Move;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy {
    private HashMap<Integer , HashMap<Character , Integer>> colMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if(!colMaps.containsKey(col)){
            colMaps.put(col , new HashMap<>());
        }

        HashMap<Character , Integer> currColMaps = colMaps.get(col);
        if(!currColMaps.containsKey(aChar)){
            currColMaps.put(aChar , 0);
        }

        currColMaps.put(aChar , currColMaps.get(aChar) + 1);
        return currColMaps.get(aChar).equals(board.getDimension());
    }
}
