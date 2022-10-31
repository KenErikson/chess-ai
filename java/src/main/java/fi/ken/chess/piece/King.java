package fi.ken.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import fi.ken.chess.Board;
import fi.ken.chess.Direction;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

public class King extends Piece {

    protected King( Team team ) {
        super( PieceType.KING, team );
    }

    @Override
    public Set<PiecePosition> getAllPossibleMoves(Board board, PiecePosition piecePosition) {
        Set<PiecePosition> possibleMoves = new HashSet<>();

        for ( Direction value : Direction.values() ) {
            PiecePosition potentialPosition = piecePosition.step( value, 1 );
            if ( potentialPosition == null ) {
                break;
            }
            Piece potentialPositionPiece = board.getPiece( potentialPosition );
            if ( potentialPositionPiece == null ) {
                possibleMoves.add( potentialPosition );
            }
            else {
                if ( isEnemyTeam( potentialPositionPiece.getTeam() ) ) {
                    possibleMoves.add( potentialPosition );
                }
                break;
            }
        }

        return ImmutableSet.copyOf( possibleMoves );
    }
}
