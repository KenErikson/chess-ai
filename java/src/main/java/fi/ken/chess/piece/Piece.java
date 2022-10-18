package fi.ken.chess.piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
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

    public static Optional<Piece> pieceFor( PieceType pieceType, Team team ) {
        return PIECES_LOOKUP.stream()
                .filter( p -> p.getType() == pieceType )
                .filter( p -> p.getTeam() == team )
                .findAny();
    }

    public static Optional<Piece> pieceFor( char pieceNotation ) {
        Team team = Character.isUpperCase( pieceNotation ) ? Team.WHITE : Team.BLACK;
        PieceType pieceType = PieceType.forNotation( pieceNotation );
        return pieceFor( pieceType, team );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper( this )
                .add( "type", type )
                .add( "team", team )
                .toString();
    }

    public abstract Set<PiecePosition> getPossibleMoves(Board board, PiecePosition piecePosition);
}
