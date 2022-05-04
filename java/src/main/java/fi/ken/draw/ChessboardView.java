package fi.ken.draw;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import fi.ken.chess.Board;
import fi.ken.chess.Team;
import fi.ken.chess.piece.Piece;
import fi.ken.chess.piece.PieceType;
import fi.ken.draw.component.ChessboardPanel;
import fi.ken.draw.image.PieceImageLoader;
import fi.ken.draw.mouse.MouseHandler;

public class ChessboardView {

    private JFrame frame;
    private ChessboardPanel chessPanel;

    public ChessboardView() {
        // Make ui not look 1987
        FlatLightLaf.setup( new FlatArcOrangeIJTheme() );

        //Create and set up the window.
        frame = initFrame();

        chessPanel = new ChessboardPanel();

        frame.add( chessPanel );

        //Display the window.
        finalizeFrame( frame );
    }

    public void setBoard( Board board ) throws URISyntaxException, IOException {
        chessPanel.removeAll();

        Piece[] boardState = board.getState();
        for ( int i = 0; i < boardState.length; i++ ) {
            Piece piece = boardState[i];
            if ( piece != null ) {
                JLabel picLabel = loadPictureLabel( piece );

                chessPanel.add( picLabel );
                picLabel.setLocation( 200, 200 );
            }
        }

        //        Piece piece = Piece.pieceFor( PieceType.ROOK, Team.WHITE );
        //        JLabel picLabel = loadPictureLabel( piece );

        //        chessPanel.add( picLabel );
    }

    private static JLabel loadPictureLabel( Piece piece ) throws URISyntaxException, IOException {
        File imageFile = PieceImageLoader.getImageFor( piece );
        BufferedImage myPicture = ImageIO.read( imageFile );

        JLabel picLabel = new JLabel( new ImageIcon( myPicture ) );
        MouseHandler.setMouseHandler( picLabel );

        return picLabel;
    }

    private static JFrame initFrame() {
        JFrame frame = new JFrame( "ChessBoard22" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.addKeyListener( getEscExitKeyListener() );
        frame.setMinimumSize( new Dimension( 840, 860 ) );
        frame.setLocation( 400, 200 );
        return frame;
    }

    private static void finalizeFrame( JFrame frame ) {
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
}
