package fi.ken;

import java.io.IOException;
import java.net.URISyntaxException;

import fi.ken.chess.Board;
import fi.ken.chess.ChessboardModel;
import fi.ken.draw.ChessboardView;

import javax.annotation.Nullable;

public class Controller {

    private ChessboardModel boardModel;
    private ChessboardView boardView;

    public Controller( ChessboardModel boardModel, ChessboardView boardView ) throws URISyntaxException, IOException {
        this.boardModel = boardModel;
        this.boardView = boardView;
        initView();
    }

    public void initController() throws URISyntaxException, IOException, InterruptedException {
        updateView();
    }

    public void initView() {
        // anything?
    }

    public void updateBoard(Board board) throws URISyntaxException, IOException, InterruptedException {
        this.boardModel.setBoard( board );
        updateView();
    }

    public void updateView() throws URISyntaxException, IOException, InterruptedException {
        boardView.setBoard( boardModel.getBoard(), -1, this );
    }

    public void updateView( int indexSelected ) throws URISyntaxException, IOException, InterruptedException {
        boardView.setBoard( boardModel.getBoard(), indexSelected, this );
    }
}
