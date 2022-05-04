package fi.ken;

import fi.ken.chess.Board;
import fi.ken.chess.ChessboardModel;
import fi.ken.chess.FenNotation;
import fi.ken.draw.ChessboardView;

import static fi.ken.chess.FenNotation.STARTING_FEN;

import java.io.IOException;
import java.net.URISyntaxException;

public class ChessApp {

    public static void main( String[] args ) throws URISyntaxException, IOException {
        Board board = FenNotation.boardFromParsedFen( STARTING_FEN );

        ChessboardModel boardModel = new ChessboardModel( board );
        ChessboardView boardView = new ChessboardView();
        Controller controller = new Controller( boardModel, boardView );
        controller.initController();
    }

}
