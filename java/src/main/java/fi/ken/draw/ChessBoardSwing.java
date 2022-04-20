package fi.ken.draw;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

public class ChessBoardSwing {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() throws IOException {

        // Make ui not look 1987
        FlatLightLaf.setup( new FlatArcOrangeIJTheme() );

        //Create and set up the window.
        JFrame frame = new JFrame( "HelloWorldSwing" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.addKeyListener( getEscExitKeyListener() );
        frame.setMinimumSize( new Dimension( 840, 860 ) );
        frame.setLocation( 400, 200 );

        //        //Add the ubiquitous "Hello World" label.
        //        JLabel label = new JLabel( "Hello World" );
        //        frame.getContentPane().add( label );
        //
        //        JButton jButton = new JButton( "TMP" );
        //        frame.getContentPane().add( jButton );

        //        URL url = new URL( "http://i.stack.imgur.com/OVOg3.jpg" );
        //        final BufferedImage bg = ImageIO.read( url );

        ChessboardPanel c = new ChessboardPanel();
        c.setLayout( new GridLayout( 0, 5, 16, 16 ) );
        c.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
        //        for ( int ii = 1; ii < 26; ii++ ) {
        //            //            c.add( new JButton( "Button " + ii ) );
        //        }
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
                catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        } );
    }
}