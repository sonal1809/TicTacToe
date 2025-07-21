package org.example.controller;

import org.example.Strategies.winningStrategy.WinningStrategy;
import org.example.exception.InvalidMoveException;
import org.example.models.Board;
import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;

import java.util.List;

public class GameController {

    public Game startGame(int dimension , List<Player> players , List<WinningStrategy> winningStrategies){
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies).build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState gameState(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void undo(){

    }
}
