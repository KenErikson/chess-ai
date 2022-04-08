package fi.ken.chess.piece;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

/**
 * Fen notation https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
 *
 * pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K"
 */
public enum PieceType {
    PAWN( "pawn", 'P', 0 ),
    KNIGHT( "knight", 'N', 1 ),
    BISHOP( "bishop", 'B', 2 ),
    ROOK( "rook", 'R', 3 ),
    QUEEN( "queen", 'Q', 4 ),
    KING( "king", 'K', 5 ),
    ;

    private static final Map<Character, PieceType> LOOKUP = Arrays.stream( PieceType.values() )
            .collect(
                    toMap( pieceType -> pieceType.notation, pieceType -> pieceType )
            );

    private String name;
    private char notation;
    private int id;

    PieceType( String name, char notation, int id ) {
        this.name = name;
        this.notation = notation;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public char getNotation() {
        return notation;
    }

    public int getId() {
        return id;
    }

    public static PieceType forNotation( char notation ) {
        PieceType pieceType = LOOKUP.get( Character.toUpperCase( notation ) );
        if ( pieceType == null ) {
            throw new IllegalArgumentException( "Notation unknown: '" + notation + "'" );
        }
        return pieceType;
    }
}
