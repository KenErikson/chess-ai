package fi.ken.chess;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import javax.annotation.Nullable;

public class PiecePosition {

    private final int row;
    private final int column;

    private PiecePosition( int row, int column ) {
        checkArgument( row >= 0 && row < Board.BOARD_SIDE_LENGTH, "Row invalid: " + row );
        checkArgument( column >= 0 && column < Board.BOARD_SIDE_LENGTH, "Col invalid: " + column );
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int toIndex() {
        return ( row * ( Board.BOARD_SIDE_LENGTH ) ) + column;
    }

    public boolean atLeftEdge( Team team ) {
        return team == Team.WHITE ? column == 0 : column == Board.BOARD_SIDE_LENGTH - 1;
    }

    public boolean atRightEdge( Team team ) {
        return atLeftEdge( team == Team.WHITE ? Team.BLACK : Team.WHITE );
    }

    @Nullable
    public static PiecePosition of( int index ) {
        if ( index < 0 || index >= Board.BOARD_SIZE ) {
            return null;
        }
        return new PiecePosition( toRow( index ), toColumn( index ) );
    }

    private static int toRow( int index ) {
        return index / Board.BOARD_SIDE_LENGTH;
    }

    private static int toColumn( int index ) {
        return index % Board.BOARD_SIDE_LENGTH;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        PiecePosition that = (PiecePosition)o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash( row, column );
    }

    @Override
    public String toString() {
        return "PiecePosition{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public PiecePosition withRow( int newRow ) {
        if ( newRow != row ) {
            return new PiecePosition( newRow, column );
        }
        return this;
    }

    public PiecePosition withColumn( int newColumn ) {
        if ( newColumn != column ) {
            return new PiecePosition( row, newColumn );
        }
        return this;
    }

    public static PiecePosition of( int row, int column ) {
        return new PiecePosition( row, column );
    }

    public PiecePosition forward( int steps, Team team ) {
        System.out.println( "Pawn to " + steps + " for team " + team + " possible" );
        checkNotNull( team, "Team must be set" );
        checkArgument( steps != 0, "Steps must not be 0: " + steps );
        int stepsDirected = team == Team.BLACK ? steps : steps * -1;
        int newRow = row + stepsDirected;
        checkArgument( newRow >= 0 && newRow < Board.BOARD_SIDE_LENGTH, "New Row not on board: " + newRow );
        return withRow( newRow );
    }

    public PiecePosition backwards( int steps, Team team ) {
        return forward( steps * -1, team );
    }

    public PiecePosition left( int steps, Team team ) {
        System.out.println( "Pawn Left to " + steps + " for team " + team + " possible" );
        checkNotNull( team, "Team must be set" );
        checkArgument( steps != 0, "Steps must nor be 0: " + steps );
        int stepsDirected = team == Team.BLACK ? steps : steps * -1;
        int newColumn = column + stepsDirected;
        checkArgument( newColumn >= 0 && newColumn < Board.BOARD_SIDE_LENGTH, "New Column not on board: " + newColumn );
        return withColumn( newColumn );
    }

    public PiecePosition right( int steps, Team team ) {
        return left( steps * -1, team );
    }

    @Nullable
    public PiecePosition step( Direction direction, int steps ) {
        checkNotNull( direction, "direction must be set" );
        checkArgument( steps > 0, "Steps must be > 0: " + steps );
        System.out.println( "STEP: " + direction + " - " + steps );
        int newRow = row;
        int newColumn = column;
        if ( direction == Direction.UP || direction == Direction.DOWN ) {
            if ( direction == Direction.UP ) {
                steps = steps * -1;
            }
            newRow = row + steps;
        }
        else if ( direction == Direction.LEFT || direction == Direction.RIGHT ) {
            if ( direction == Direction.LEFT ) {
                steps = steps * -1;
            }
            newColumn = column + steps;
        }
        else {
            if ( direction.getKey().contains( "right" ) ) {
                newColumn = column + steps;
            }
            else {
                newColumn = column - steps;
            }
            if ( direction.getKey().contains( "down" ) ) {
                newRow = row + steps;
            }
            else {
                newRow = row - steps;
            }
        }

        try {
            return withRow( newRow ).withColumn( newColumn );
        }
        catch ( IllegalArgumentException e ) {
            return null;
        }
    }
}
