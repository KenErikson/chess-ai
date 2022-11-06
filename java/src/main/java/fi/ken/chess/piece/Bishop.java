package fi.ken.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import fi.ken.chess.Board;
import fi.ken.chess.Direction;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

public class Bishop extends Piece {

    protected Bishop( Team team ) {
        super( PieceType.BISHOP, team );
    }

    @Override
    public Set<PiecePosition> getAllPossibleMoves( Board board, PiecePosition piecePosition ) {
        Set<PiecePosition> possibleMoves = new HashSet<>();

        for ( Direction value : Direction.DIAGONAL_DIRECTIONS ) {
            for ( int steps = 1; steps < Board.BOARD_SIDE_LENGTH; steps++ ) {
                PiecePosition potentialPosition = piecePosition.step( value, steps );
                if ( potentialPosition == null ) {
                    break;
                }
                addPossibleMoveIfEmptyOrEnemy( board, possibleMoves, potentialPosition );
            }
        }

        return ImmutableSet.copyOf( possibleMoves );
    }
}
