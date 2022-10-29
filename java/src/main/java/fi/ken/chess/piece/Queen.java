package fi.ken.chess.piece;

import com.google.common.collect.ImmutableSet;
import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

import java.util.Set;

public class Queen extends Piece {

    protected Queen( Team team ) {
        super( PieceType.QUEEN, team );
    }

    @Override
    public Set<PiecePosition> getAllPossibleMoves(Board board, PiecePosition piecePosition) {
        return ImmutableSet.of();
    }
}
