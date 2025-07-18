package org.example.models;

import org.example.Strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private Player winner;
    private List<Move> moves;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder(){
        return new Builder();
    }

    private Game(int dimension , List<Player> players , List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public  void printBoard(){
        board.printBoard();
    }



    public Move makeMove(){
        return null;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        private Builder(){
            this.players = new ArrayList<>();
            this.dimension = 0;
            this.winningStrategies = new ArrayList<>();
        }

        private void validateBotCount(){
            int count = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    count++;
                    if(count > 1){
                        throw new RuntimeException("Only one BOT is allowed per game");
                    }
                }
            }
        }

        private void validateUniqueSymbols() {
            Set<Character> symbolSet = new HashSet<>();
            for (Player player : players) {
                symbolSet.add(player.getSymbol().getaChar());
            }
            if (symbolSet.size() != dimension - 1) {
                throw new RuntimeException("Every player should have a unique symbol");
            }
        }

        private void validations(){
           validateBotCount();
           validateUniqueSymbols();
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build(){
            validations();
            return new Game(dimension , players, winningStrategies);
        }

    }
}
