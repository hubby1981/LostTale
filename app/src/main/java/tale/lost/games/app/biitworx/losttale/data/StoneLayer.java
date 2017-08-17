package tale.lost.games.app.biitworx.losttale.data;

/**
 * Created by mweissgerber on 17.08.2017.
 */

public class StoneLayer extends Layer {
    public StoneLayer(int dimens) {
        super(dimens);
    }

    @Override
    public GameObject generate(int x,int y) {
        return new Stone();
    }
}
