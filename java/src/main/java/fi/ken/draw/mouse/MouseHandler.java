package fi.ken.draw.mouse;

import fi.ken.Controller;
import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.piece.Piece;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JLabel;

public class MouseHandler extends MouseAdapter {

    private final Controller controller;
    private final Piece piece;
    private final PiecePosition piecePosition;

    public MouseHandler(Piece piece, PiecePosition piecePosition, Controller controller) {
        this.piece = piece;
        this.piecePosition = piecePosition;
        this.controller = controller;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        //        JLabel label = (JLabel)e.getComponent();
        //        moveToFront( label );
        // offset = e.getPoint();

        System.out.println("Pressed " + piece.getType() + " on index " + piecePosition);
        try {
            controller.updateView( piecePosition );
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
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

    public static void setMouseHandler(JLabel jLabel, Piece piece, PiecePosition piecePosition, Controller controller) {
        MouseHandler mh = new MouseHandler( piece, piecePosition, controller);

        jLabel.addMouseListener( mh );
        jLabel.addMouseMotionListener( mh );
    }
}
