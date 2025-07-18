package org.example.Strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements  WinningStrategy{
    private final Map<Integer , HashMap<Character , Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row , new HashMap<>());
        }

        if(!rowMaps.get(row).containsKey(aChar)){
            rowMaps.get(row).put(aChar , 0);
        }
        rowMaps.get(row).put(aChar , rowMaps.get(row).get(aChar) + 1);

        return false;
    }
}
