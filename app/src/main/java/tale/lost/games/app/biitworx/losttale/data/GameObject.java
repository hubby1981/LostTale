package tale.lost.games.app.biitworx.losttale.data;

import java.util.ArrayList;

/**
 * Created by mweissgerber on 17.08.2017.
 */

public abstract class GameObject {

    private int moveable = 0;
    private int destroyable = 0;
    public String name ="";
    protected ArrayList<GameObject> objects=new ArrayList<>();

    public GameObject(){
        createSubObjects();
    }


    protected GameObject setMoveable(int moveable) {
        this.moveable = moveable;
        return this;
    }

    protected GameObject setDestroyable(int destroyable) {
        this.destroyable = destroyable;
        return this;
    }

    protected abstract void createSubObjects();


    public ArrayList<GameObject> getObjects(){
        return objects;
    }
}
