package tale.lost.games.app.biitworx.losttale.data;

import java.util.ArrayList;

/**
 * Created by mweissgerber on 17.08.2017.
 */

public abstract class Layer {
    private ArrayList<ArrayList<GameObject>> objects;

    public Layer(int dimens) {
        objects = new ArrayList<>();

        for (int y = 0; y < dimens; y++) {
            ArrayList<GameObject> obj=new ArrayList<>();

            for (int x = 0; x < dimens; x++) {

                GameObject o = generate(x,y);
                o.name = x + "." + y;
                obj.add(o);
            }
            objects.add(obj);

        }
    }

    public abstract GameObject generate(int x,int y);

    public GameObject get(int x, int y) {
        return objects.get(y).get(x);
    }
}
