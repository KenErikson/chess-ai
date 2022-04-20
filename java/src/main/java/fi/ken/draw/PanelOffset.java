package fi.ken.draw;

import java.io.Serial;
import java.io.Serializable;

public class PanelOffset implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int xOffset;
    private final int yOffset;

    public PanelOffset( int xOffset, int yOffset ) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
}
