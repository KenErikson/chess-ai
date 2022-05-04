package fi.ken.chess;

public class ChessboardModel {

    private Board board;

    public ChessboardModel( Board board ) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard( Board board ) {
        this.board = board;
    }
}
