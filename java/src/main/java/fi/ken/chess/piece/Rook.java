package fi.ken.chess.piece;

import com.google.common.collect.ImmutableSet;
import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

import java.util.Set;

public class Rook extends Piece {

    protected Rook( Team team ) {
        super( PieceType.ROOK, team );
    }

    @Override
    public Set<PiecePosition> getPossibleMoves(Board board, PiecePosition piecePosition) {
        return ImmutableSet.of();
    }
}
