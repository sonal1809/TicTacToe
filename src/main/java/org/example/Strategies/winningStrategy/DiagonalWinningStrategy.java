package org.example.Strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Move;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{
    // 2 diagonal
    private final HashMap<Character , Integer> leftDiagonalMap = new HashMap<>(); // Starting from 0,0
    private final HashMap<Character , Integer> rightDiagonalMap = new HashMap<>(); // Starting from 0,n-1

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        // Condition for left diagonal - > row == col
        if(row == col){
            if(!leftDiagonalMap.containsKey(aChar)){
                leftDiagonalMap.put(aChar , 0);
            }
            leftDiagonalMap.put(aChar , leftDiagonalMap.get(aChar) + 1);
        }


        // Condition for left diagonal - > row + col == N-1
        if(row + col == board.getDimension()-1){
            if(!rightDiagonalMap.containsKey(aChar)){
                rightDiagonalMap.put(aChar , 0);
            }
            rightDiagonalMap.put(aChar , rightDiagonalMap.get(aChar) + 1);
        }

        if( (row == col && leftDiagonalMap.get(aChar) == board.getDimension()) ||
                ( row + col == board.getDimension() -1 &&
                        rightDiagonalMap.get(aChar) == board.getDimension() -1)) {
            return true;
        }

        return false;
    }
}
