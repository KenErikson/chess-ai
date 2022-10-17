package fi.ken.draw.mouse;

import fi.ken.Controller;
import fi.ken.chess.Board;
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
    private final int index;

    public MouseHandler(Piece piece, int index, Controller controller) {
        this.piece = piece;
        this.index = index;
        this.controller = controller;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        //        JLabel label = (JLabel)e.getComponent();
        //        moveToFront( label );
        // offset = e.getPoint();

        System.out.println("Pressed " + piece.getType() + " on index " + index);
        try {
            controller.updateView( index );
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

    public static void setMouseHandler(JLabel jLabel, Piece piece, int index, Controller controller) {
        MouseHandler mh = new MouseHandler( piece, index, controller);

        jLabel.addMouseListener( mh );
        jLabel.addMouseMotionListener( mh );
    }
}
