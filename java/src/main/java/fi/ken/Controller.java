package fi.ken;

import java.io.IOException;
import java.net.URISyntaxException;

import fi.ken.chess.Board;
import fi.ken.chess.ChessboardModel;
import fi.ken.chess.PiecePosition;
import fi.ken.draw.ChessboardView;

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

    public void updateBoard( Board board ) throws URISyntaxException, IOException, InterruptedException {
        this.boardModel.setBoard( board );
        updateView();
    }

    public void updateView() throws URISyntaxException, IOException, InterruptedException {
        boardView.setBoard( boardModel.getBoard(), null, this );
    }

    public void updateView( PiecePosition selectedPosition ) throws URISyntaxException, IOException, InterruptedException {
        boardView.setBoard( boardModel.getBoard(), selectedPosition, this );
    }

    public void move( PiecePosition movingPiecePosition, PiecePosition moveToPosition ) throws URISyntaxException, IOException, InterruptedException {
        Board board = boardModel.getBoard();

        Board newBoard = board.move( movingPiecePosition, moveToPosition );

        boolean isCheck = newBoard.isTeamInCheck( newBoard.getTeamToMove() );
        boolean isCheckMate = isCheck && !newBoard.teamHasValidMoves( newBoard.getTeamToMove() );
        if ( isCheckMate ) {
            System.out.println( "CHECKMATE" );
        }
        else if ( isCheck ) {
            System.out.println( "CHECK" );
        }

        updateBoard( newBoard );
    }
}
