package Models;

import Strategy.Winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private int currentMovePlayer;
    private List<WinningStrategy> winningStrategys;
    private GameStatus gameStatus;
    private Player winner;

    public static Builder getBuilder(){
        return new Builder();
    }

    private Game(int n,List<Player> players,List<WinningStrategy> winningStrategys){
        this.moves = new ArrayList<>();
        this.board = new Board(n);
        this.players = players;
        this.currentMovePlayer = 0;
        this.winningStrategys = winningStrategys;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winner = null;
    }

    public boolean validateMove(Cell cell){
        int row = cell.getRow();
        int col = cell.getCol();
        if(row >= board.getSize() || col >= board.getSize() || row < 0 || col < 0){
            return false;
        }
        if(board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY)){
            return true;
        }
        return false;
    }

    public void makeMove(){
        Player currPlayer = players.get(currentMovePlayer);
        System.out.println("Current Player: "+currPlayer.getName());
        Cell proposedCell = currPlayer.makeMove(board);
        System.out.println("Moved made at row: "+proposedCell.getRow() + " Col: "+proposedCell.getCol());
        if (!validateMove(proposedCell)){
            System.out.println("Invalid Move: "+currPlayer.getName());
            return;
        }
        Cell cell = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cell.setCellStatus(CellStatus.FILLED);
        cell.setPlayer(currPlayer);


        Move move = new Move(currPlayer,cell);
        moves.add(move);

        if (checkGameWinner(currPlayer, move)) return;

        if (checkGameDraw()) return;

        currentMovePlayer += 1 ;
        currentMovePlayer = currentMovePlayer%players.size();

    }

    public void undo(){
        if (moves.size() == 0 ){
            System.out.println("no moves");
            return;
        }
        Move lastmove = moves.get(moves.size()-1);
        Cell cell =  lastmove.getCell();
        cell.setCellStatus(CellStatus.EMPTY);
        cell.setPlayer(null);
        moves.remove(lastmove);
        for(WinningStrategy winningStrategy: winningStrategys){
            winningStrategy.handleUndo(board,lastmove);
        }

        currentMovePlayer -=1;
        currentMovePlayer += players.size();
        currentMovePlayer = currentMovePlayer%players.size();
    }

    private boolean checkGameDraw() {
        if(moves.size() == board.getSize() * board.getSize()){
            gameStatus = GameStatus.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkGameWinner(Player currPlayer, Move move) {
        for (WinningStrategy winningStrategy : winningStrategys){
            if(winningStrategy.checkWinner(board, move)){
                gameStatus = GameStatus.ENDED;
                winner = currPlayer;
                return true;
            }
        }
        return false;
    }

    public void printBoard(){
        this.board.print();
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentMovePlayer() {
        return currentMovePlayer;
    }

    public void setCurrentMovePlayer(int currentMovePlayer) {
        this.currentMovePlayer = currentMovePlayer;
    }

    public List<WinningStrategy> getWinningStrategys() {
        return winningStrategys;
    }

    public void setWinningStrategys(List<WinningStrategy> winningStrategys) {
        this.winningStrategys = winningStrategys;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public  void printResult(){
        if(gameStatus.equals(GameStatus.ENDED)){
            System.out.println("Game has ended");
            System.out.println("Winner is : "+ winner.getName());
        }
        else{
            System.out.println("Game is draw");
        }
    }
    public static class Builder{
        int dimension;
        List<Player> players;
        List<WinningStrategy> winningStrategies;

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

        private Builder(){

        }

        private boolean valid(){
            if(this.players.size() < 2)
                return false;
            if( this.players.size() != this.dimension -1)
                return false;

            int bot = 0;
            for (Player player : this.players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    bot += 1;
                }
            }

            if(bot >=2)
                return false;

            Set<Character> symbols = new HashSet<>();
            for (Player player : this.players){
                if (symbols.contains(player.getSymbol().getaChar()))
                    return false;
                symbols.add(player.getSymbol().getaChar());
            }
            return true;
        }

        public Game build(){
            if(!valid()){
                throw new RuntimeException("Invalid parameter");
            }
            return new Game(dimension,players,winningStrategies);
        }


    }

}
