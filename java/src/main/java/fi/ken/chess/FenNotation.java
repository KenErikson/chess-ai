package fi.ken.chess;

import static com.google.common.base.Preconditions.checkArgument;
import static fi.ken.chess.Board.BOARD_SIDE_LENGTH;
import static java.util.stream.Collectors.toSet;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import fi.ken.chess.piece.Piece;

/**
 * Fen notation (know double notation, but more readable to me)
 *
 * https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
 */
public class FenNotation {

    private static final String STARTING_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    // "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

    private static final Splitter FEN_SPACE_SPLITTER = Splitter.on( " " ).omitEmptyStrings().trimResults();
    private static final Splitter FEN_SLASH_SPLITTER = Splitter.on( "/" ).omitEmptyStrings().trimResults();

    public static Board boardFromParsedFen( String fen ) {

        List<String> splittedFen = FEN_SPACE_SPLITTER.splitToList( fen );

        checkArgument( splittedFen.size() == 6, "Fen of unexpected length: " + splittedFen.size() );

        String boardStateStr = splittedFen.get( 0 );
        String teamToMoveStr = splittedFen.get( 1 );
        String castlingStr = splittedFen.get( 2 );
        String enPassantStr = splittedFen.get( 3 );
        int captureLessHalfmoves = Integer.parseInt( splittedFen.get( 4 ) );
        int moveCount = Integer.parseInt( splittedFen.get( 5 ) );

        checkArgument( teamToMoveStr.length() == 1 );
        checkArgument( enPassantStr.length() >= 1 && enPassantStr.length() <= 2 );
        checkArgument( castlingStr.length() >= 1 && castlingStr.length() <= 4 );

        Team teamToMove = Team.forNotation( teamToMoveStr.charAt( 0 ) );
        Set<CastlingType> availableCastling = parseCastling( castlingStr );
        int enPassant = parseEnpassant( enPassantStr );

        Piece[] state = parseBoard( boardStateStr );

        return new Board( state, teamToMove, availableCastling, enPassant, captureLessHalfmoves, moveCount );
    }

    private static Piece[] parseBoard( String boardStateStr ) {
        Piece[] state = new Piece[Board.BOARD_SIZE];

        List<String> rowList = FEN_SLASH_SPLITTER.splitToList( boardStateStr );

        checkArgument( rowList.size() == 8 );

        int index = 0;
        for ( String row : rowList ) {
            for ( char pieceNotation : row.toCharArray() ) {
                if ( Character.isDigit( pieceNotation ) ) {
                    index += Integer.parseInt( String.valueOf( pieceNotation ) );
                }
                else {
                    state[index] = null;
                    index++;
                }
            }
        }

        return state;
    }

    private static final Pattern A_TO_H_CHARACTER_PATTERN = Pattern.compile( "^[a-h]$" );
    private static final Pattern ONE_TO_EIGHT_CHARACTER_PATTERN = Pattern.compile( "^[1-8]$" );

    private static int parseEnpassant( String enPassantStr ) {
        if ( !Strings.isNullOrEmpty( enPassantStr ) && !enPassantStr.equals( "-" ) ) {
            checkArgument( enPassantStr.length() == 2 );
            char char1 = enPassantStr.charAt( 0 );
            char char2 = enPassantStr.charAt( 1 );
            checkArgument( A_TO_H_CHARACTER_PATTERN.matcher( String.valueOf( char1 ) ).matches() );
            checkArgument( ONE_TO_EIGHT_CHARACTER_PATTERN.matcher( String.valueOf( char2 ) ).matches() );

            int x = char1 - 'a';
            int y = char2;

            return x + y * BOARD_SIDE_LENGTH;
        }
        return -1;
    }

    private static Set<CastlingType> parseCastling( String castlingStr ) {
        if ( !Strings.isNullOrEmpty( castlingStr ) && !castlingStr.equals( "-" ) ) {
            Set<CastlingType> castlingTypes = castlingStr.chars()
                    .mapToObj( c -> (char)c )
                    .map( CastlingType::forNotation )
                    .collect( toSet() );
            return EnumSet.copyOf( castlingTypes );
        }
        return EnumSet.noneOf( CastlingType.class );
    }
}
