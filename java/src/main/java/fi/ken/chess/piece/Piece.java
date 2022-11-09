package fi.ken.chess.piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nullable;

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

    public Set<PiecePosition> getPossibleMoves( Board board, PiecePosition piecePosition ) {
        Set<PiecePosition> allPossibleMoves = getAllPossibleMoves( board, piecePosition );

        return filterValidMovesForTeam( allPossibleMoves, board, piecePosition, getTeam() );
    }

    public abstract Set<PiecePosition> getAllPossibleMoves( Board board, PiecePosition piecePosition );

    protected boolean isEnemyTeam( Team team ) {
        return getTeam() != team;
    }

    /**
     * @return true if is enemy, otherwise false
     */
    protected PossiblePositionResult addPossibleMoveIfEmptyOrEnemy( Board board, Set<PiecePosition> possibleMoves, @Nullable PiecePosition potentialPosition ) {
        if ( potentialPosition == null ) {
            return PossiblePositionResult.OUT_OF_BOUNDS;
        }
        Piece potentialPositionPiece = board.getPiece( potentialPosition );
        if ( potentialPositionPiece == null ) {
            possibleMoves.add( potentialPosition );
            return PossiblePositionResult.EMPTY;
        }
        else if ( isEnemyTeam( potentialPositionPiece.getTeam() ) ) {
            possibleMoves.add( potentialPosition );
            return PossiblePositionResult.ENEMY;
        }
        else {
            return PossiblePositionResult.FRIENDLY;
        }
    }

    private static Set<PiecePosition> filterValidMovesForTeam( Set<PiecePosition> allPossibleMoves, Board board, PiecePosition piecePosition, Team team ) {
        Set<PiecePosition> validMoves = new HashSet<>();
        for ( PiecePosition possibleMove : allPossibleMoves ) {
            boolean isValidMove = isValidMove( possibleMove, board, piecePosition, team );
            if ( isValidMove ) {
                validMoves.add( possibleMove );
            }
        }
        return ImmutableSet.copyOf( validMoves );
    }

    private static boolean isValidMove( PiecePosition possibleMove, Board board, PiecePosition piecePosition, Team team ) {
        Board newBoard = board.move( piecePosition, possibleMove );
        return newBoard.isValidForTeam( team );
    }
}
