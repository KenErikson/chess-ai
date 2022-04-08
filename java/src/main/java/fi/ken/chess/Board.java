package fi.ken.chess;

import java.util.EnumSet;
import java.util.Set;

import fi.ken.chess.piece.Piece;

/**
 * Board representation
 *
 * Fen notation https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
 */
public class Board {

    private static final int boardSideLength = 8;
    private static final int boardSize = boardSideLength * boardSideLength;

    private final Piece[] state = new Piece[boardSize];

    private final Team teamToMove;

    private final Set<CastlingType> availableCastling;

    private final int enPassantIndex;

    public Board() {
        initState( state );

        teamToMove = Team.WHITE;
        availableCastling = EnumSet.allOf( CastlingType.class );
        enPassantIndex = -1;
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

    private static void initState( Piece[] state ) {
        String tmp = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    }
}
