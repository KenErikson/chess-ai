package fi.ken.chess.piece;

import fi.ken.chess.Team;

public abstract class Piece {

    private final PieceType type;
    private final Team team;

    protected Piece( PieceType type, Team team ) {
        this.type = type;
        this.team = team;
    }
}
