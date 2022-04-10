package fi.ken.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

import fi.ken.chess.Team;

public abstract class Piece {

    private static final Set<Piece> PIECES_LOOKUP;

    static {
        Set<Piece> piecesSet = new HashSet<>();
        for ( Team team : Team.values() ) {
            piecesSet.add( new Pawn( team ) );
            piecesSet.add( new Bishop( team ) );
            piecesSet.add( new Knight( team ) );
            piecesSet.add( new Rook( team ) );
            piecesSet.add( new Queen( team ) );
            piecesSet.add( new King( team ) );
        }

        PIECES_LOOKUP = ImmutableSet.copyOf( piecesSet );
    }

    private final PieceType type;
    private final Team team;

    protected Piece( PieceType type, Team team ) {
        this.type = type;
        this.team = team;
    }

    public PieceType getType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }

    public static Piece pieceFor( PieceType pieceType, Team team ) {
        return PIECES_LOOKUP.stream()
                .filter( p -> p.getType() == pieceType )
                .filter( p -> p.getTeam() == team )
                .findAny()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("team", team)
                .toString();
    }
}
