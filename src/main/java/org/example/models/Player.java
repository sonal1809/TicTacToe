package org.example.models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    private static Scanner scanner = new Scanner(System.in);

    public Player(Symbol symbol, PlayerType playerType, String name) {
        this.symbol = symbol;
        this.playerType = playerType;
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Move makeMove(Board board){
        // Take row , col in the input from the player
        System.out.println("Please enter the row number where you want to make a move");
        int row = scanner.nextInt();

        System.out.println("Please enter the col number where you want to make a move");
        int col = scanner.nextInt();

        return new Move(this , new Cell(row,col));

    }
}
