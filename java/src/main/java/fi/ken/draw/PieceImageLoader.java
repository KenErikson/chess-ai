package fi.ken.draw;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import fi.ken.chess.Team;
import fi.ken.chess.piece.Piece;

public class PieceImageLoader {

    public static File getImageFor( Piece piece ) throws URISyntaxException {
        String filename = "Chess_";
        filename += Character.toLowerCase( piece.getType().getNotation() );
        filename += Team.WHITE == piece.getTeam() ? "l" : "d";
        filename += "t45.png";

        String pathToImage = "chess_icons/" + filename;
        URL imageUrl = Thread.currentThread().getContextClassLoader().getResource(pathToImage);
        return new File( imageUrl.toURI() );
    }
}
