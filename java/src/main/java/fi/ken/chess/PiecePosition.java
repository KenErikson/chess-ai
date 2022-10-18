package fi.ken.chess;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class PiecePosition {

    private final int row;
    private final int column;

    private PiecePosition(int row, int column) {
        checkArgument(row >= 0 && row < Board.BOARD_SIDE_LENGTH , "Row invalid: " + row);
        checkArgument(column >= 0 && column < Board.BOARD_SIDE_LENGTH, "Col invalid: " + column);
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int toIndex(){
        return row * Board.BOARD_SIDE_LENGTH + column;
    }

    public boolean atLeftEdge(){
        return column == 0;
    }

    public  boolean atRightEdge(){
        return column == Board.BOARD_SIDE_LENGTH-1;
    }

    @Nullable
    public static PiecePosition of(int index){
        if( index < 0 || index >= Board.BOARD_SIZE){
            return null;
        }
        return new PiecePosition(toRow(index), toColumn(index));
    }

    private static int toRow(int index){
        return index / Board.BOARD_SIDE_LENGTH;
    }

    private static int toColumn(int index){
        return index % Board.BOARD_SIDE_LENGTH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiecePosition that = (PiecePosition) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "PiecePosition{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public PiecePosition withRow( int newRow ){
        return new PiecePosition( newRow, column );
    }

    public PiecePosition withColumn( int newColumn ){
        return new PiecePosition( row, newColumn );
    }

    public static PiecePosition of( int row,int column ){
        return new PiecePosition( row, column );
    }

    public PiecePosition forward(int steps, Team team) {
        System.out.println("Pawn to " + steps + " for team " + team + " possible");
        checkNotNull( team, "Team must be set" );
        checkArgument( steps > 0, "Steps must be positive: " + steps);
        int stepsDirected = team == Team.BLACK ? steps : steps * -1;
        int newRow = row + stepsDirected;
        checkArgument(newRow>=0 && newRow< Board.BOARD_SIDE_LENGTH - 1, "New Row not on board: " + newRow);
        return withRow( newRow );
    }

    public PiecePosition backwards(int steps, Team team) {
        return forward( steps * -1, team );
    }
}
