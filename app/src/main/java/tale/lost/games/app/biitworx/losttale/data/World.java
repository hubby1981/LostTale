package tale.lost.games.app.biitworx.losttale.data;

import java.util.ArrayList;

/**
 * Created by mweissgerber on 17.08.2017.
 */

public class World {
    private ArrayList<Layer> layers = new ArrayList<>();
    public static int dimens = 512;
    public static int worldX=0;
    public static int worldY=0;

    public void generate() {
        layers.add(new StoneLayer(dimens));
        layers.add(new StoneLayer(dimens));
        layers.add(new StoneLayer(dimens));
        layers.add(new StoneLayer(dimens));
        layers.add(new GrassLayer(dimens));

    }

    public GameObject get(int layer, int x, int y) {
        if (layers.size() <= layer) return null;
        return layers.get(layer).get(x, y);
    }
}
