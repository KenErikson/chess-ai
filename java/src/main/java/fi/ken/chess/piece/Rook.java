package fi.ken.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import fi.ken.chess.Board;
import fi.ken.chess.Direction;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

public class Rook extends Piece {

    protected Rook( Team team ) {
        super( PieceType.ROOK, team );
    }

    @Override
    public Set<PiecePosition> getAllPossibleMoves( Board board, PiecePosition piecePosition ) {
        Set<PiecePosition> possibleMoves = new HashSet<>();

        for ( Direction direction : Direction.ORTHOGONAL_DIRECTIONS ) {
            for ( int steps = 1; steps < Board.BOARD_SIDE_LENGTH; steps++ ) {
                PiecePosition potentialPosition = piecePosition.step( direction, steps );

                PossiblePositionResult possiblePositionResult = addPossibleMoveIfEmptyOrEnemy( board, possibleMoves, potentialPosition );
                if ( possiblePositionResult != PossiblePositionResult.EMPTY ) {
                    break;
                }
            }
        }

        return ImmutableSet.copyOf( possibleMoves );
    }
}
