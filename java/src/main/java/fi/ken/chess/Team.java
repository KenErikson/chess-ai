package fi.ken.chess;

public enum Team {
    WHITE( "white", 'w' ),
    BLACK( "black", 'b' ),
    ;

    private final String name;
    private final char notation;

    Team( String name, char notation ) {
        this.name = name;
        this.notation = notation;
    }

    public String getName() {
        return name;
    }

    public static Team forPieceNotation( char notation ) {
        if ( Character.isUpperCase( notation ) ) {
            return WHITE;
        }
        return BLACK;
    }

    public static Team forNotation( char notation ) {
        if ( notation == WHITE.notation ) {
            return WHITE;
        }
        if ( notation == BLACK.notation ) {
            return BLACK;
        }
        throw new IllegalArgumentException( "Unknown Notation: " + notation );
    }
}
