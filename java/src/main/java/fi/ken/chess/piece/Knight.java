package fi.ken.chess.piece;

import com.google.common.collect.ImmutableSet;
import fi.ken.chess.Board;
import fi.ken.chess.PiecePosition;
import fi.ken.chess.Team;

import java.util.Set;

public class Knight extends Piece {

    protected Knight( Team team ) {
        super( PieceType.KNIGHT, team );
    }

    @Override
    public Set<PiecePosition> getPossibleMoves(Board board, PiecePosition piecePosition) {
        return ImmutableSet.of();
    }
}
