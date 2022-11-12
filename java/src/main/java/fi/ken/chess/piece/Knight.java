package fi.ken.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import fi.ken.chess.Board;
import fi.ken.chess.Direction;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

public class Knight extends Piece {

    protected Knight( Team team ) {
        super( PieceType.KNIGHT, team );
    }

    @Override
    public Set<PiecePosition> getAllPossibleMoves( Board board, PiecePosition piecePosition ) {
        Set<PiecePosition> possibleMoves = new HashSet<>();

        for ( Direction direction : Direction.ORTHOGONAL_DIRECTIONS ) {
            int steps = 2;
            PiecePosition potentialPosition = piecePosition.step( direction, steps );
            if ( potentialPosition == null ) {
                continue;
            }

            Set<Direction> testDirections;
            switch ( direction ) {
                case UP:
                case DOWN:
                    testDirections = ImmutableSet.of( Direction.LEFT, Direction.RIGHT );
                    break;
                case LEFT:
                case RIGHT:
                    testDirections = ImmutableSet.of( Direction.UP, Direction.DOWN );
                    break;
                default:
                    throw new IllegalArgumentException( "only orthigonal expected, not: " + direction );
            }
            for ( Direction testDirection : testDirections ) {
                PiecePosition testPotentialPosition = potentialPosition.step( testDirection, 1 );
                addPossibleMoveIfEmptyOrEnemy( board, possibleMoves, testPotentialPosition );
            }
        }

        return ImmutableSet.copyOf( possibleMoves );
    }
}
