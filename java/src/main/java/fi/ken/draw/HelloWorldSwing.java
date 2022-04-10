package fi.ken.draw;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

public class HelloWorldSwing {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

        // Make ui not look 1987
        FlatLightLaf.setup( new FlatArcOrangeIJTheme() );

        //Create and set up the window.
        JFrame frame = new JFrame( "HelloWorldSwing" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setMinimumSize( new Dimension( 200, 200 ) );

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel( "Hello World" );
        frame.getContentPane().add( label );

        JButton jButton = new JButton( "TMP" );
        frame.getContentPane().add( jButton );

        //Display the window.
        frame.pack();
        frame.setVisible( true );
    }

    public static void main( String[] args ) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        } );
    }
}