package fi.ken.chess;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

public enum CastlingType {
    WHITE_KINGSIDE( "White Kingside", 'K' ),
    WHITE_QUEENSIDE( "White Queen", 'Q' ),
    BLACK_KINGSIDE( "Black Kingside", 'k' ),
    BLACK_QUEENSIDE( "Black Queenside", 'q' ),
    ;

    private static final Map<Character, CastlingType> LOOKUP = Arrays.stream( CastlingType.values() )
            .collect(
                    toMap( castling -> castling.fenChar, castlingType -> castlingType )
            );

    private final String name;
    private final char fenChar;

    CastlingType( String name, char fenChar ) {
        this.name = name;
        this.fenChar = fenChar;
    }

    public String getName() {
        return name;
    }

    public static CastlingType forNotation( char notation ) {
        CastlingType castlingType = LOOKUP.get( notation );
        if ( castlingType == null ) {
            throw new IllegalArgumentException( "Notation unknown: '" + notation + "'" );
        }
        return castlingType;
    }
}
