package fi.ken.chess.piece;

import com.google.common.collect.ImmutableSet;
import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

import java.util.Set;

public class King extends Piece {

    protected King( Team team ) {
        super( PieceType.KING, team );
    }

    @Override
    public Set<PiecePosition> getPossibleMoves(Board board, PiecePosition piecePosition) {
        return ImmutableSet.of();
    }
}
