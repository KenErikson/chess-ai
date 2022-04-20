package fi.ken.draw;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import fi.ken.chess.Team;
import fi.ken.chess.piece.Piece;
import fi.ken.chess.piece.PieceType;

public class ChessBoardSwing {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() throws IOException, URISyntaxException {

        // Make ui not look 1987
        FlatLightLaf.setup( new FlatArcOrangeIJTheme() );

        //Create and set up the window.
        JFrame frame = new JFrame( "ChessBoard22" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.addKeyListener( getEscExitKeyListener() );
        frame.setMinimumSize( new Dimension( 840, 860 ) );
        frame.setLocation( 400, 200 );

        ChessboardPanel c = new ChessboardPanel();
                c.setLayout( new GridLayout( 0, 5, 16, 16 ) );
        //        c.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );

        File imageFile = PieceImageLoader.getImageFor( Piece.pieceFor( PieceType.ROOK, Team.WHITE ) );
        BufferedImage myPicture = ImageIO.read( imageFile );
        JLabel picLabel = new JLabel( new ImageIcon( myPicture ) );
        picLabel.setLocation(  );
        c.add( picLabel );

        frame.add( c );

        //Display the window.
        frame.pack();
        frame.setVisible( true );
    }

    private static KeyListener getEscExitKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed( KeyEvent evt ) {
                //If someone click Esc key, this program will exit
                if ( evt.getKeyCode() == KeyEvent.VK_ESCAPE ) {
                    System.exit( 0 );
                }
            }
        };

    }

    public static void main( String[] args ) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                try {
                    createAndShowGUI();
                }
                catch ( IOException | URISyntaxException e ) {
                    e.printStackTrace();
                }
            }
        } );
    }
}