package fi.ken.chess;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import fi.ken.chess.piece.Piece;

/**
 * Board representation
 */
public class Board {

    public static final int BOARD_SIDE_LENGTH = 8;
    public static final int BOARD_SIZE = BOARD_SIDE_LENGTH * BOARD_SIDE_LENGTH;

    private final Piece[] state;

    private final Team teamToMove;

    private final Set<CastlingType> availableCastling;

    private final int enPassantIndex;

    private final int captureLessHalfmoveCount;

    private final int moveCount;

    Board( Piece[] state, Team teamToMove, Set<CastlingType> availableCastling, int enPassantIndex, int captureLessHalfmoveCount, int moveCount ) {
        // Validate valid board
        checkArgument( state.length == BOARD_SIZE );
        checkNotNull( teamToMove );
        checkNotNull( availableCastling );
        checkArgument(  availableCastling.size() <= 4 );
        checkArgument( enPassantIndex > -1 && enPassantIndex < BOARD_SIZE );
        checkArgument( captureLessHalfmoveCount >= 0 );
        checkArgument( moveCount >= 0 );

        this.state = state;
        this.teamToMove = teamToMove;
        this.availableCastling = availableCastling;
        this.enPassantIndex = enPassantIndex;
        this.captureLessHalfmoveCount = captureLessHalfmoveCount;
        this.moveCount = moveCount;
    }

    public static Board startingBoard(){
        return FenNotation.boardFromParsedFen( FenNotation.STARTING_FEN );
    }

    public Piece[] getState() {
        return state;
    }

    public Team getTeamToMove() {
        return teamToMove;
    }

    public Set<CastlingType> getAvailableCastling() {
        return availableCastling;
    }

    public int getEnPassantIndex() {
        return enPassantIndex;
    }

    public int getCaptureLessHalfmoveCount() {
        return captureLessHalfmoveCount;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
