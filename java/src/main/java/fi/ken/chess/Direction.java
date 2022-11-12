package fi.ken.chess;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public enum Direction {
    UP( "up" ),
    DOWN( "down" ),
    LEFT( "left" ),
    RIGHT( "right" ),
    UP_LEFT( "up-left" ),
    UP_RIGHT( "up-right" ),
    DOWN_LEFT( "down-left" ),
    DOWN_RIGHT( "down-right" ),
    ;

    public static final Set<Direction> DIAGONAL_DIRECTIONS = ImmutableSet.of( UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT );
    public static final Set<Direction> ORTHOGONAL_DIRECTIONS = ImmutableSet.of( UP, DOWN, LEFT, RIGHT );

    private final String key;

    Direction( String key ) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static Direction forwardDirectionForTeam( Team team ) {
        return team == Team.WHITE ? UP : DOWN;
    }

    public static Direction backwardsDirectionForTeam( Team team ) {
        return forwardDirectionForTeam( team ).inverse();
    }

    public static Direction leftDirectionForTeam( Team team ) {
        return team == Team.WHITE ? LEFT : RIGHT;
    }

    public static Direction rightDirectionForTeam( Team team ) {
        return leftDirectionForTeam( team ).inverse();
    }

    private Direction inverse() {
        switch ( this ) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP_LEFT:
                return DOWN_RIGHT;
            case UP_RIGHT:
                return DOWN_LEFT;
            case DOWN_LEFT:
                return UP_RIGHT;
            case DOWN_RIGHT:
                return UP_LEFT;
            default:
                throw new IllegalStateException( "Unknown direction" );
        }
    }
}
