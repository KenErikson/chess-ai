package fi.ken.draw.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import javax.annotation.Nullable;
import javax.swing.JLabel;

import fi.ken.Controller;
import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.piece.Piece;

public class MouseHandler extends MouseAdapter {

    private final Controller controller;
    private final Piece piece;
    private final PiecePosition piecePosition;
    private final Board board;
    private final Set<PiecePosition> possibleMoves;
    private final PiecePosition selectedPosition;

    public MouseHandler( Piece piece, PiecePosition piecePosition, Controller controller, Board board, Set<PiecePosition> possibleMoves, PiecePosition selectedPosition ) {
        this.piece = piece;
        this.piecePosition = piecePosition;
        this.controller = controller;
        this.board = board;
        this.possibleMoves = possibleMoves;
        this.selectedPosition = selectedPosition;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        //        JLabel label = (JLabel)e.getComponent();
        //        moveToFront( label );
        // offset = e.getPoint();

        System.out.println( "Pressed " + ( piece != null ? piece.getType() : "empty" ) + " on index " + piecePosition );

        if ( piece != null && piece.getTeam() == board.getTeamToMove() ) {
            try {
                if ( piecePosition.equals( selectedPosition ) ) {
                    controller.updateView( null );
                }
                else {
                    controller.updateView( piecePosition );
                }
            }
            catch ( URISyntaxException | IOException | InterruptedException ex ) {
                throw new RuntimeException( ex );
            }
        }
        else if ( possibleMoves.contains( piecePosition ) ) {
            try {
                controller.move( selectedPosition, piecePosition );
            }
            catch ( URISyntaxException | IOException | InterruptedException ex ) {
                throw new RuntimeException( ex );
            }
        }

    }

    @Override
    public void mouseDragged( MouseEvent e ) {
        //        int x = e.getPoint().x - offset.x;
        //        int y = e.getPoint().y - offset.y;
        //        Component component = e.getComponent();
        //        Point location = component.getLocation();
        //        location.x += x;
        //        location.y += y;
        //        component.setLocation( location );
    }

    public static void setMouseHandler( JLabel jLabel, @Nullable Piece piece, PiecePosition piecePosition, Controller controller, Board board, Set<PiecePosition> possibleMoves, PiecePosition selectedPosition ) {
        MouseHandler mh = new MouseHandler( piece, piecePosition, controller, board, possibleMoves, selectedPosition );

        jLabel.addMouseListener( mh );
        jLabel.addMouseMotionListener( mh );
    }
}
