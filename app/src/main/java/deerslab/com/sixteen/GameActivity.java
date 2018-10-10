package deerslab.com.sixteen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by keeper on 19.01.2016.
 */
public class GameActivity extends Activity {

    private GameView gameView;

    public static Activity first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        gameView.hasSaveState = settings.getBoolean("save_state", false);

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("hasState")) {
                load();
            }
        }

        setContentView(gameView);

        first = this;
    }

    private void save(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        Cell field[][]=gameView.game.field;

        for (int xx=0; xx<4; xx++) {
            for (int yy = 0; yy < 4; yy++) {
                editor.putInt(xx+""+yy, field[xx][yy].getValue());
            }
        }
        editor.putInt("MOVES", gameView.turnsCount);
        editor.putBoolean("save_state", true);
        editor.putBoolean("newGame", false);
        editor.apply();
    }

    private void load(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        Cell field[][]= new Cell[4][4];

        for (int xx=0; xx<4; xx++) {
            for (int yy = 0; yy < 4; yy++) {
                field[xx][yy] = new Cell(gameView.cellSize, xx, yy,settings.getInt(xx + " " + yy, -1));
            }
        }
        if (!settings.getBoolean("newGame", false)) {
            gameView.turnsCount = settings.getInt("MOVES", 0);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("hasState", true);
        save();
    }

    protected void onPause() {
        super.onPause();
        save();
    }

    protected void onResume() {
        super.onResume();
        load();
    }

}
