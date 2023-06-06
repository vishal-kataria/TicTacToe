package Models;

public class Cell {
    private int row,col;

    private CellStatus cellStatus;
    private  Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellStatus = CellStatus.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void display(){
        if (getCellStatus().equals(CellStatus.EMPTY)){
            System.out.print(" - |");
        }
        else{
            System.out.print(" "+getPlayer().getSymbol().getaChar() + " |");
        }
    }
}
