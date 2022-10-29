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
        homeRow = ( team == Team.WHITE ) ? 6 : 1;
    }

    private boolean hasMoved( PiecePosition currentPosition ) {
        return currentPosition.getRow() != homeRow;
    }

    @Override
    public Set<PiecePosition> getAllPossibleMoves( Board board, PiecePosition selectedPosition ) {
        Set<PiecePosition> possibleMoves = new HashSet<>();

        boolean moved = hasMoved( selectedPosition );
        PiecePosition forwardOne = forward( 1, selectedPosition );
        if ( board.getPiece( forwardOne ) == null ) {
            possibleMoves.add( forwardOne );

            if ( !moved ) {
                PiecePosition forwardTwo = forward( 2, selectedPosition );
                if ( board.getPiece( forwardTwo ) == null ) {
                    possibleMoves.add( forwardTwo );
                }
            }
        }

        if ( !selectedPosition.atLeftEdge( getTeam() ) ) {
            PiecePosition forwardLeft = forwardOne.left( 1, getTeam() );
            Piece potentialTakePiece = board.getPiece( forwardLeft );
            if ( potentialTakePiece != null && potentialTakePiece.isEnemyTeam( getTeam() ) ) {
                possibleMoves.add( forwardLeft );
            }
            else if ( board.getEnPassantPosition().equals( forwardLeft ) ) {
                possibleMoves.add( forwardLeft );
            }

        }
        if ( !selectedPosition.atRightEdge( getTeam() ) ) {
            PiecePosition forwardRight = forwardOne.right( 1, getTeam() );
            Piece potentialTakePiece = board.getPiece( forwardRight );
            if ( potentialTakePiece != null && potentialTakePiece.isEnemyTeam( getTeam() ) ) {
                possibleMoves.add( forwardRight );
            }
            else if ( board.getEnPassantPosition().equals( forwardRight ) ) {
                possibleMoves.add( forwardRight );
            }
        }

        return ImmutableSet.copyOf( possibleMoves );
    }

    private PiecePosition forward( int steps, PiecePosition selectedPosition ) {
        return selectedPosition.forward( steps, getTeam() );
    }
}
