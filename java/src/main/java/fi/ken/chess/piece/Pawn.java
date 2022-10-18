package fi.ken.chess.piece;

import com.google.common.collect.ImmutableSet;
import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    private final int homeRow;

    protected Pawn( Team team ) {
        super( PieceType.PAWN, team );
        homeRow = (team == Team.WHITE) ? 6 : 1;
    }

    private boolean hasMoved( PiecePosition currentPosition){
        return currentPosition.getRow() != homeRow;
    }

    @Override
    public Set<PiecePosition> getPossibleMoves(Board board, PiecePosition selectedPosition) {
        Set<PiecePosition> possibleMoves = new HashSet<>();

        boolean moved = hasMoved(selectedPosition);
        PiecePosition forwardOne = forward(1, selectedPosition);
        if( board.getPiece(forwardOne) == null ){
            possibleMoves.add(forwardOne);

            if(!moved){
                PiecePosition forwardTwo = forward(2, selectedPosition);
                if( board.getPiece(forwardTwo) == null){
                    possibleMoves.add( forwardTwo );
                }
            }
        }

        // for all possible moves - board.isBoardValidAfterMove( selectedPosition, movePosition );
        Set<PiecePosition> possibleAndValidMoves = new HashSet<>( possibleMoves );
        return ImmutableSet.copyOf( possibleAndValidMoves );
    }

    private PiecePosition forward( int steps, PiecePosition selectedPosition) {
        return selectedPosition.forward(steps, getTeam());
    }
}
