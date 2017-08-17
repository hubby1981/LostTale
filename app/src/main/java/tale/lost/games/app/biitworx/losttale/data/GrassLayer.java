package tale.lost.games.app.biitworx.losttale.data;

import android.graphics.Color;

import java.util.Random;

import tale.lost.games.app.biitworx.losttale.MainActivity;

/**
 * Created by mweissgerber on 17.08.2017.
 */

public class GrassLayer extends Layer {
    public GrassLayer(int dimens) {
        super(dimens);
    }

    @Override
    public GameObject generate(int x, int y) {


        int pixel = MainActivity.layer4.getPixel(x, y);

        int r = Color.red(pixel);
        int g = Color.green(pixel);
        int b = Color.blue(pixel);

        GameObject result = new Stone();
        if (r==0&&g==255&&b==0) {
            result=new Grass();
        } else if (r==0&&g==0&&b==255) {

            result =  new Water();
        }else if (r==0&&g==128&&b==0) {

            result =  new Grass();
            result.getObjects().add(new Willow());
        }
        else if (r==255&&g==255&&b==0) {

            result =  new Sand();
        }

        return result;
    }


    private boolean isBW(int r,int g,int b,int rr,int gg,int bb){
        return ((r>rr-5&&r<rr+5) && (g>gg-5&&g<gg+5) &&(b>bb-5&&b<bb+5));
    }
}
