package tale.lost.games.app.biitworx.losttale.data;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;

import tale.lost.games.app.biitworx.losttale.MainActivity;

/**
 * Created by mweissgerber on 17.08.2017.
 */

public class Layer {
    private ArrayList<ArrayList<GameObject>> objects;
    private int layer = 0;
    public Layer(int dimens,int layer) {
        this.layer = layer;
        objects = new ArrayList<>();

        for (int y = 0; y < dimens; y++) {
            ArrayList<GameObject> obj=new ArrayList<>();

            for (int x = 0; x < dimens; x++) {

                GameObject o =null;/* generate(x,y);
                if(o!=null)
                    o.name = x + "." + y;*/
                obj.add(o);
            }
            objects.add(obj);

        }
    }

    public  GameObject generate(int x,int y){

        Bitmap map = layer==0?MainActivity.layer0:
                        layer==1?MainActivity.layer1:
                                layer==2?MainActivity.layer2:
                                        layer==3?MainActivity.layer3:MainActivity.layer4;
        int pixel = map.getPixel(x, y);

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
        else if (r==0&&g==100&&b==0) {

            result =  new GrassStair();
        }
        else if (r==255&&g==100&&b==0) {

            result =  new Grass();
            result.getObjects().add(new Brick());
        }
        else if (r==255&&g==255&&b==255) {

            result =  null;
        }

        return result;
    }

    public GameObject get(int x, int y) {


        GameObject o =  objects.get(y).get(x);
        if (o==null){
            o = generate(x,y);
            objects.get(y).set(x,o);
        }
        return o;
    }
}
