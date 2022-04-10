package fi.ken;

import fi.ken.chess.Board;
import fi.ken.chess.FenNotation;
import fi.ken.chess.Team;
import fi.ken.chess.piece.Piece;
import fi.ken.chess.piece.PieceType;
import fi.ken.chess.piece.Rook;

import static fi.ken.chess.FenNotation.STARTING_FEN;

public class Main {

    public static void main( String[] args ) {
        FenNotation.boardFromParsedFen( STARTING_FEN );
        //
        //        Piece[] state = board.getState();
        //
        //        System.out.println( board.getAvailableCastling() );
        //        for ( Piece piece : state ) {
        //            System.out.println( piece );
        //        }
        System.out.println( Piece.pieceFor( PieceType.KING, Team.BLACK ) );
    }

}
