package fi.ken.chess;

public enum CastlingType {
    WHITE_KINGSIDE( "White Kingside" ),
    WHITE_QUEENSIDE( "White Queen" ),
    BLACK_KINGSIDE( "Black Kingside" ),
    BLACK_QUEENSIDE( "Black Queenside" ),
    ;

    private final String name;

    CastlingType( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
