package fi.ken;

import fi.ken.chess.Board;
import fi.ken.chess.piece.Piece;

public class Main {

    public static void main( String[] args ) {
        Board board = new Board();

        Piece[] state = board.getState();

        System.out.println( board.getAvailableCastling() );
        for ( Piece piece : state ) {
            System.out.println( piece );
        }
    }

}
