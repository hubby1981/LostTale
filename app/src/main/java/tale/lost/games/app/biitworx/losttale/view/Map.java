package tale.lost.games.app.biitworx.losttale.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import tale.lost.games.app.biitworx.losttale.MainActivity;
import tale.lost.games.app.biitworx.losttale.R;
import tale.lost.games.app.biitworx.losttale.data.Brick;
import tale.lost.games.app.biitworx.losttale.data.GameObject;
import tale.lost.games.app.biitworx.losttale.data.Grass;
import tale.lost.games.app.biitworx.losttale.data.GrassStair;
import tale.lost.games.app.biitworx.losttale.data.PositionRect;
import tale.lost.games.app.biitworx.losttale.data.Sand;
import tale.lost.games.app.biitworx.losttale.data.Stone;
import tale.lost.games.app.biitworx.losttale.data.Water;
import tale.lost.games.app.biitworx.losttale.data.Willow;

/**
 * Created by mweissgerber on 04.08.2017.
 */

public class Map extends View {
    private Rect rc;
    private Rect left;
    private Rect up;
    private Rect right;
    private Rect down;

    private LinearGradient grad;
    private Paint back;
    private Paint text;
    private Bitmap stone;
    private Bitmap grass;
    private Bitmap sand;
    private Bitmap willow;
    private Bitmap leavesgreen;
    private Bitmap water;
    private Bitmap grassstairs;
    private Bitmap brick;

    private int size = 80;
    private ArrayList<ArrayList<PositionRect>> layers = null;

    public Map(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rc = new Rect(0, 0, 0, 0);
        back = new Paint();
        back.setStyle(Paint.Style.FILL);
        stone = BitmapFactory.decodeResource(MainActivity.res, R.drawable.stone);
        sand = BitmapFactory.decodeResource(MainActivity.res, R.drawable.sand);
        grass = BitmapFactory.decodeResource(MainActivity.res, R.drawable.grass);
        willow = BitmapFactory.decodeResource(MainActivity.res, R.drawable.willow);
        leavesgreen = BitmapFactory.decodeResource(MainActivity.res, R.drawable.leavesgreen);
        water = BitmapFactory.decodeResource(MainActivity.res, R.drawable.water);
        grassstairs = BitmapFactory.decodeResource(MainActivity.res, R.drawable.grassstairs);
        brick = BitmapFactory.decodeResource(MainActivity.res, R.drawable.brick);

        text = new Paint();
        text.setStyle(Paint.Style.FILL_AND_STROKE);
        text.setColor(Color.BLACK);
        text.setTextSize(size);
    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.getClipBounds(rc);


        up = new Rect(rc.left, rc.top, rc.right, rc.top + rc.height() / 4);
        down = new Rect(rc.left, rc.bottom - rc.height() / 4, rc.right, rc.bottom);

        left = new Rect(rc.left, up.bottom, rc.left + rc.width() / 2, down.top);
        right = new Rect(rc.right - rc.width() / 2, up.bottom, rc.right, down.top);

        if (grad == null)
            grad = new LinearGradient(rc.left, rc.top, rc.left, rc.bottom, Color.argb(255, 180, 230, 255), Color.argb(255, 40, 115, 130), Shader.TileMode.CLAMP);
        back.setShader(grad);
        canvas.drawRect(rc, back);

        if (layers == null) {
            layers = new ArrayList<>();
            layers.add(drawLayer(new Rect(rc.left, rc.top + size * 3, rc.right, rc.bottom + size * 3)));

            layers.add(drawLayer(new Rect(rc.left, rc.top + size * 2, rc.right, rc.bottom + size * 2)));
            layers.add(drawLayer(new Rect(rc.left, rc.top + size, rc.right, rc.bottom + size)));

            layers.add(drawLayer(rc));
            layers.add(drawLayer(new Rect(rc.left, rc.top - size, rc.right, rc.bottom - size)));
        }

        if (layers != null) {
            int index = 0;
            for (ArrayList<PositionRect> layer : layers) {
                drawList(canvas, layer, index);
                index++;
            }

        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (left.contains((int) event.getX(), (int) event.getY())) {
                MainActivity.world.worldX -= 1;
                if (MainActivity.world.worldX < 0)
                    MainActivity.world.worldX = 0;
            }
            if (up.contains((int) event.getX(), (int) event.getY())) {
                MainActivity.world.worldY -= 1;
                if (MainActivity.world.worldY < 0)
                    MainActivity.world.worldY = 0;
            }


            if (right.contains((int) event.getX(), (int) event.getY())) {
                MainActivity.world.worldX += 1;
                if (MainActivity.world.worldX >=MainActivity.world.dimens)
                    MainActivity.world.worldX = MainActivity.world.dimens;
            }

            if (down.contains((int) event.getX(), (int) event.getY())) {
                MainActivity.world.worldY += 1;
                if (MainActivity.world.worldY >=MainActivity.world.dimens)
                    MainActivity.world.worldY = MainActivity.world.dimens;
            }
            this.invalidate();
            return true;
        }
        return false;
    }

    private void drawList(Canvas canvas, ArrayList<PositionRect> layer, int index) {
        int x = MainActivity.world.worldX;
        int y = MainActivity.world.worldY;


        for (PositionRect rc : layer) {

            GameObject go = MainActivity.world.get(index, x + rc.getX(), y + rc.getY());
            if (go != null) {
                if (go.getClass() == Stone.class) {
                    canvas.drawBitmap(stone, null, rc.getPos(), null);
                } else if (go.getClass() == Grass.class) {
                    canvas.drawBitmap(grass, null, rc.getPos(), null);
                } else if (go.getClass() == Sand.class) {
                    canvas.drawBitmap(sand, null, rc.getPos(), null);
                } else if (go.getClass() == Water.class) {
                    canvas.drawBitmap(water, null, rc.getPos(), null);
                }
                else if (go.getClass() == GrassStair.class) {
                    canvas.drawBitmap(grassstairs, null, rc.getPos(), null);
                }
                else if (go.getClass() == Brick.class) {
                    canvas.drawBitmap(brick, null, rc.getPos(), null);
                }
                if (go.getObjects().size() > 0) {

                    for (GameObject go2 : go.getObjects()) {
                        if (go2.getClass() == Willow.class) {

                            RectF from = new RectF(rc.getPos().centerX() - rc.getPos().width() / 4, rc.getPos().centerY() - rc.getPos().height() / 1.75f, rc.getPos().centerX() + rc.getPos().width() / 4, (rc.getPos().centerY() - rc.getPos().height() / 1.75f) + rc.getPos().width() / 2);

                            canvas.drawBitmap(willow, null, from, null);
                            from = new RectF(from.left, from.top - from.height() / 2, from.right, from.bottom - from.height() / 2);
                            canvas.drawBitmap(willow, null, from, null);
                            from = new RectF(from.left, from.top - from.height() / 2, from.right, from.bottom - from.height() / 2);
                            canvas.drawBitmap(willow, null, from, null);
                            from = new RectF(from.left - from.width() / 2, (from.top - from.height() / 2) - from.width() / 2, from.right + from.width() / 2, (from.bottom - from.height() / 2) + from.width() / 2);
                            canvas.drawBitmap(leavesgreen, null, from, null);
                        }
                    }
                }

            }


        }
    }


    private ArrayList<PositionRect> drawLayer(Rect rc) {
        ArrayList<PositionRect> result = new ArrayList<>();
        //ebene -4
        result.add(gr(rc, 0, -4, 0, 0));

        //ebene -3
        result.add(gr(rc, -1, -3, 0, 1));
        result.add(gr(rc, 1, -3, 1, 0));

        //ebene -2
        result.add(gr(rc, -2, -2, 0, 2));
        result.add(gr(rc, 0, -2, 1, 1));
        result.add(gr(rc, 2, -2, 2, 0));

        //ebene -1
        result.add(gr(rc, -3, -1, 0, 3));
        result.add(gr(rc, -1, -1, 1, 2));
        result.add(gr(rc, 1, -1, 2, 1));
        result.add(gr(rc, 3, -1, 3, 0));

        //ebene 0 center
        result.add(gr(rc, -4, 0, 0, 4));
        result.add(gr(rc, -2, 0, 1, 3));
        result.add(gr(rc, 0, 0, 2, 2));
        result.add(gr(rc, 2, 0, 3, 1));
        result.add(gr(rc, 4, 0, 4, 0));

        //ebene 1
        result.add(gr(rc, -3, 1, 1, 4));
        result.add(gr(rc, -1, 1, 2, 3));
        result.add(gr(rc, 1, 1, 3, 2));
        result.add(gr(rc, 3, 1, 4, 1));

        //ebene 2
        result.add(gr(rc, -2, 2, 2, 4));
        result.add(gr(rc, 0, 2, 3, 3));
        result.add(gr(rc, 2, 2, 4, 2));

        //ebene 3
        result.add(gr(rc, -1, 3, 3, 4));
        result.add(gr(rc, 1, 3, 4, 3));

        //ebene 4
        result.add(gr(rc, 0, 4, 4, 4));
        return result;
    }

    private PositionRect gr(Rect rc, int x, int y, int lx, int ly) {

        int posx = rc.centerX();
        int posy = rc.centerY();

        if (x != 0) {
            posx += (x * size);
        }
        if (y != 0) {
            posy += (y * size) / 2;
        }


        return new PositionRect(new RectF(posx - size, posy - size, posx + size, posy + size), lx, ly);
    }
}
