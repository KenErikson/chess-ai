package fi.ken.draw.component;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serial;

import javax.swing.JPanel;

import fi.ken.chess.Board;

public class ChessboardPanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Color LIGHT_BOARD_COLOR = Color.decode( "#FFCE9E" );
    private static final Color DARK_BOARD_COLOR = Color.decode( "#D18B47" );

    private int height;
    private int width;
    private int squareSide;
    private PanelOffset boardOffset;

    public void init() {
        width = getWidth();
        height = getHeight();
        squareSide = getSquareSide( width, height );
        boardOffset = getBoardOffset( width, height, squareSide );
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        if ( isWindowSizeChanged() ) {
            this.init();
        }

        for ( int y = 0; y < Board.BOARD_SIDE_LENGTH; y++ ) {
            for ( int x = 0; x < Board.BOARD_SIDE_LENGTH; x++ ) {
                g.setColor( ( x + y ) % 2 == 0 ? LIGHT_BOARD_COLOR : DARK_BOARD_COLOR );
                g.fillRect( boardOffset.getxOffset() + x * squareSide, boardOffset.getyOffset() + y * squareSide, squareSide, squareSide );
            }
        }

    }

    private boolean isWindowSizeChanged() {
        return width != getWidth() || height != getHeight();
    }

    private static int getSquareSide( int width, int height ) {
        return Math.min( width, height ) / Board.BOARD_SIDE_LENGTH;
    }

    private static PanelOffset getBoardOffset( int width, int height, int squareSide ) {
        int xOffset, yOffset;
        if ( width > height ) {
            xOffset = ( width - squareSide * Board.BOARD_SIDE_LENGTH ) / 2;
            yOffset = 0;
        }
        else {
            xOffset = 0;
            yOffset = ( height - squareSide * Board.BOARD_SIDE_LENGTH ) / 2;
        }
        return new PanelOffset( xOffset, yOffset );
    }
}