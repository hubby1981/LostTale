package tale.lost.games.app.biitworx.losttale.data;

import android.graphics.RectF;

/**
 * Created by mweissgerber on 17.08.2017.
 */

public class PositionRect {
    private RectF pos;
    private int y = 0;
    private int x = 0;

    public PositionRect(RectF pos, int x, int y) {
        this.pos = pos;
        this.x = x;
        this.y = y;
    }


    public RectF getPos() {
        return pos;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
