package fi.ken.chess;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import fi.ken.chess.piece.Piece;
import fi.ken.chess.piece.PieceType;

/**
 * Board representation
 */
public class Board {

    public static final int BOARD_SIDE_LENGTH = 8;
    public static final int BOARD_SIZE = BOARD_SIDE_LENGTH * BOARD_SIDE_LENGTH;

    private final Piece[] state;

    private final Team teamToMove;

    private final Set<CastlingType> availableCastling;

    private final PiecePosition enPassantPosition;

    private final int captureLessHalfmoveCount;

    private final int moveCount;

    Board( Piece[] state, Team teamToMove, Set<CastlingType> availableCastling, @Nullable PiecePosition enPassantPosition, int captureLessHalfmoveCount, int moveCount ) {
        // Validate valid board
        checkArgument( state.length == BOARD_SIZE );
        checkNotNull( teamToMove );
        checkNotNull( availableCastling );
        checkArgument( availableCastling.size() <= 4 );
        checkArgument( captureLessHalfmoveCount >= 0 );
        checkArgument( moveCount >= 0 );

        this.state = state;
        this.teamToMove = teamToMove;
        this.availableCastling = availableCastling;
        this.enPassantPosition = enPassantPosition;
        this.captureLessHalfmoveCount = captureLessHalfmoveCount;
        this.moveCount = moveCount;
    }

    public static Board startingBoard() {
        return FenNotation.boardFromParsedFen( FenNotation.STARTING_FEN );
    }

    public Piece[] getState() {
        return state;
    }

    public Team getTeamToMove() {
        return teamToMove;
    }

    public Set<CastlingType> getAvailableCastling() {
        return availableCastling;
    }

    public PiecePosition getEnPassantPosition() {
        return enPassantPosition;
    }

    public int getCaptureLessHalfmoveCount() {
        return captureLessHalfmoveCount;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public Set<PiecePosition> getPossibleMoves( @Nullable PiecePosition piecePosition ) {
        Piece selectedPiece = getPiece( piecePosition );
        if ( selectedPiece == null ) {
            return ImmutableSet.of();
        }

        return selectedPiece.getPossibleMoves( this, piecePosition );
    }

    @Nullable
    public Piece getPiece( @Nullable PiecePosition selectedPosition ) {
        if ( selectedPosition == null ) {
            return null;
        }
        return state[selectedPosition.toIndex()];
    }

    public Board move( PiecePosition movingPiecePosition, PiecePosition moveToPosition ) {
        //        System.out.println( "From" );
        //        System.out.println( movingPiecePosition );
        //        System.out.println( "To" );
        //        System.out.println( moveToPosition );
        Piece[] newState = Arrays.copyOf( state, state.length );

        Piece movingPiece = getPiece( movingPiecePosition );
        checkArgument( movingPiece != null, "Moving piece must not be null" );
        newState[movingPiecePosition.toIndex()] = null;

        Piece moveToPiece = getPiece( moveToPosition );
        newState[moveToPosition.toIndex()] = movingPiece;

        return new Board(
                newState,
                teamToMove.otherTeam(),
                availableCastling,
                null,
                moveToPiece != null ? 0 : captureLessHalfmoveCount + 1,
                moveCount + 1
        );
    }

    public boolean isTeamInCheck( Team team ) {
        List<Pair<Piece, PiecePosition>> allEnemyTeamPieces = getPieces( team.otherTeam() );

        PiecePosition kingPosition = getKing( team );

        for ( Pair<Piece, PiecePosition> enemyPiecePair : allEnemyTeamPieces ) {
            Piece enemyPiece = enemyPiecePair.getKey();
            PiecePosition piecePosition = enemyPiecePair.getValue();
            Set<PiecePosition> allPossibleMovesForPiece = enemyPiece.getAllPossibleMoves( this, piecePosition );
            if ( allPossibleMovesForPiece.contains( kingPosition ) ) {
                return true;
            }
        }

        return false;
    }

    private PiecePosition getKing( Team team ) {
        return getAllPieces().stream()
                .filter( p -> p.getKey().getType() == PieceType.KING )
                .filter( p -> p.getKey().getTeam() == team )
                .findFirst()
                .orElseThrow()
                .getValue();
    }

    private List<Pair<Piece, PiecePosition>> getPieces( Team team ) {
        return getAllPieces().stream()
                .filter( p -> p.getKey().getTeam() == team )
                .collect( Collectors.toList() );
    }

    private List<Pair<Piece, PiecePosition>> getAllPieces() {
        List<Pair<Piece, PiecePosition>> allPieces = new ArrayList<>();

        for ( int i = 0; i < BOARD_SIZE; i++ ) {
            Piece potentialPiece = state[i];
            if ( potentialPiece != null ) {
                allPieces.add( Pair.of( potentialPiece, PiecePosition.of( i ) ) );
            }
        }

        return ImmutableList.copyOf( allPieces );
    }

    public boolean teamHasValidMoves( Team team ) {
        List<Pair<Piece, PiecePosition>> allTeamPieces = getPieces( team );

        for ( Pair<Piece, PiecePosition> enemyPiecePair : allTeamPieces ) {
            Piece enemyPiece = enemyPiecePair.getKey();
            PiecePosition piecePosition = enemyPiecePair.getValue();
            Set<PiecePosition> possibleMovesForPiece = enemyPiece.getPossibleMoves( this, piecePosition );
            if ( !possibleMovesForPiece.isEmpty() ) {
                return true;
            }
        }
        return false;
    }
}
