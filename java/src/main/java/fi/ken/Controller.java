package fi.ken;

import java.io.IOException;
import java.net.URISyntaxException;

import fi.ken.chess.ChessboardModel;
import fi.ken.draw.ChessboardView;

public class Controller {

    private ChessboardModel boardModel;
    private ChessboardView boardView;

    public Controller( ChessboardModel boardModel, ChessboardView boardView ) throws URISyntaxException, IOException {
        this.boardModel = boardModel;
        this.boardView = boardView;
        initView();
    }

    public void initController() {
        // Add listeners
    }

    public void initView() throws URISyntaxException, IOException {
        boardView.setBoard( boardModel.getBoard() );
    }
}
