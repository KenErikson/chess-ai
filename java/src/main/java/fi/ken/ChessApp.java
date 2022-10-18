package fi.ken;

import fi.ken.chess.Board;
import fi.ken.chess.ChessboardModel;
import fi.ken.chess.FenNotation;
import fi.ken.draw.ChessboardView;

import static fi.ken.chess.FenNotation.STARTING_FEN;

import java.io.IOException;
import java.net.URISyntaxException;

public class ChessApp {

    public static void main( String[] args ) throws URISyntaxException, IOException, InterruptedException {
        Board board = FenNotation.boardFromParsedFen( STARTING_FEN );

        board = FenNotation.boardFromParsedFen( "rnbqkbnr/pp2pppp/1pp5/2Pp4/8/8/PP1PPPPP/RNBQKBNR w KQkq - 0 1" );

        ChessboardModel boardModel = new ChessboardModel( board );
        ChessboardView boardView = new ChessboardView();
        Controller controller = new Controller( boardModel, boardView );
        controller.initController();
    }

}
