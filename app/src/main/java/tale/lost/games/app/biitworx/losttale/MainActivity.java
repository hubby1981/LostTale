package tale.lost.games.app.biitworx.losttale;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tale.lost.games.app.biitworx.losttale.data.World;

public class MainActivity extends AppCompatActivity {
    public static Resources res;
    public static World world=new World();
    public static Bitmap layer4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res=getResources();
        layer4 = BitmapFactory.decodeResource(MainActivity.res, R.drawable.layer4);
        world.generate();

        setContentView(R.layout.activity_main);

    }
}
