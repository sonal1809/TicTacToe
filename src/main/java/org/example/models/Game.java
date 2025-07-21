package org.example.models;

import org.example.Strategies.winningStrategy.WinningStrategy;
import org.example.exception.InvalidMoveException;

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

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()){
            return false;
        }
        // Whether the cell where player is trying to move is empty is not
        if(!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return false;
        }
        return true;
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextMovePlayerIndex);
        System.out.println("This is " + currentPlayer.getName() + " 's move.");

        // Player will choose the move that they want to make
        Move move = currentPlayer.makeMove(board);

        // Game will validate if the move that the player has chosen is valid or not.
        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid move , please retry");
        }

        // Move is valid so apply this move to the board
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer , cell);
        moves.add(finalMove);

        nextMovePlayerIndex  = (nextMovePlayerIndex + 1) % players.size();

        if(checkWinner(finalMove)){
            winner = currentPlayer;
            gameState = GameState.ENDED;
        }
        else if(moves.size() == board.getDimension() * board.getDimension()){
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board , move)){
                return true;
            }
        }
        return false;
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
