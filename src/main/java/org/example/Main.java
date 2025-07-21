package org.example;

import org.example.Strategies.winningStrategy.ColumnWinningStrategy;
import org.example.Strategies.winningStrategy.DiagonalWinningStrategy;
import org.example.Strategies.winningStrategy.RowWinningStrategy;
import org.example.Strategies.winningStrategy.WinningStrategy;
import org.example.controller.GameController;
import org.example.exception.InvalidMoveException;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        GameController gameController = new GameController();
        System.out.println("GAME STARTS");
        Scanner scanner = new Scanner(System.in);
        //int dimension = scanner.nextInt();
        int dimension = 3;

        // Take players information in the input
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(new Symbol('X') , PlayerType.HUMAN , "Sonal")
        );
        players.add(
                new Bot(new Symbol('O') , PlayerType.BOT , "Scaler", BotDifficultyLevel.EASY)
        );


        // List<WinningStrategy> winningStrategies = new ArrayList<>();
        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        Game game = gameController.startGame(dimension, players, winningStrategies);

        // Let's play the game
        while (gameController.gameState(game).equals(GameState.IN_PROGRESS)){
            // 1. show the board.
            // 2. make a move.

            gameController.printBoard(game);

            System.out.println("Do you want to undo ?");
            String isUndo = scanner.next();
            if(isUndo.equalsIgnoreCase("y")) {
                // make an undo operation
                gameController.undo();
                continue;
            }
            gameController.makeMove(game);
        }

        gameController.printBoard(game);
        if (gameController.gameState(game).equals(GameState.ENDED)) {
            System.out.println(gameController.getWinner(game).getName() + " has won the game.");
        } else {
            System.out.println("GAME DRAW");
        }
    }
}