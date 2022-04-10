package fi.ken.chess.piece;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

/**
 * Piece Type
 *
 * Fen notation https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
 * pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K"
 */
public enum PieceType {
    PAWN( "pawn", 'P' ),
    KNIGHT( "knight", 'N' ),
    BISHOP( "bishop", 'B' ),
    ROOK( "rook", 'R' ),
    QUEEN( "queen", 'Q' ),
    KING( "king", 'K' ),
    ;

    private static final Map<Character, PieceType> LOOKUP = Arrays.stream( PieceType.values() )
            .collect(
                    toMap( pieceType -> pieceType.notation, pieceType -> pieceType )
            );

    private String name;
    private char notation;

    PieceType( String name, char notation ) {
        this.name = name;
        this.notation = notation;
    }

    public String getName() {
        return name;
    }

    public char getNotation() {
        return notation;
    }

    public static PieceType forNotation( char notation ) {
        PieceType pieceType = LOOKUP.get( Character.toUpperCase( notation ) );
        if ( pieceType == null ) {
            throw new IllegalArgumentException( "Notation unknown: '" + notation + "'" );
        }
        return pieceType;
    }
}
