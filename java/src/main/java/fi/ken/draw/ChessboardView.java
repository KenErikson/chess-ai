package fi.ken.draw;

import static java.awt.Image.SCALE_DEFAULT;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import fi.ken.chess.Board;
import fi.ken.chess.Team;
import fi.ken.chess.piece.Piece;
import fi.ken.chess.piece.PieceType;
import fi.ken.draw.component.ChessboardPanel;
import fi.ken.draw.component.PanelOffset;
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
        chessPanel.init();

        GridLayout gridLayout = new GridLayout( Board.BOARD_SIDE_LENGTH, Board.BOARD_SIDE_LENGTH );
        chessPanel.setLayout( gridLayout );

        PanelOffset boardOffset = chessPanel.getBoardOffset();
        int sideLength = chessPanel.getSquareSide() * Board.BOARD_SIDE_LENGTH;
        //        chessPanel.setBounds( boardOffset.getxOffset(), boardOffset.getyOffset(), sideLength, sideLength );

        frame.add( chessPanel );

        //Display the window.
        finalizeFrame( frame );
    }

    public void setBoard( Board board ) throws URISyntaxException, IOException, InterruptedException {
        while ( chessPanel.getSquareSide() <= 0 ) {
            Thread.sleep( 10L );
        }
        chessPanel.removeAll();

        Piece[] boardState = board.getState();
        for ( int i = 0; i < boardState.length; i++ ) {
            Piece piece = boardState[i];
            if ( piece != null ) {
                JLabel picLabel = loadPictureLabel( piece, chessPanel.getSquareSide() );

                //                picLabel.setSize( chessPanel.getSquareSide(), chessPanel.getSquareSide() );

                chessPanel.add( picLabel );
            }
        }

        SwingUtilities.updateComponentTreeUI( chessPanel );
    }

    private static JLabel loadPictureLabel( Piece piece, int squareSide ) throws URISyntaxException, IOException {
        File imageFile = PieceImageLoader.getImageFor( piece );
        Image img = ImageIO.read( imageFile );
        img = img.getScaledInstance( squareSide, squareSide, SCALE_DEFAULT );

        JLabel picLabel = new JLabel( new ImageIcon( img ) );
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
