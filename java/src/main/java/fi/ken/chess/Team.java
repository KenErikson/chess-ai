package fi.ken.chess;

public enum Team {
    WHITE( "white" ),
    BLACK( "black" ),
    ;

    private final String name;

    Team( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Team forNotation( char notation ) {
        if ( Character.isUpperCase( notation ) ) {
            return WHITE;
        }
        return BLACK;
    }
}
