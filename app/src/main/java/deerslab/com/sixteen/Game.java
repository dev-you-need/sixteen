package deerslab.com.sixteen;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by keeper on 19.01.2016.
 */
public class Game{

    private Context context;
    private GameView gameView;
    public Cell field [][];
    protected boolean isWin = false;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    protected int levelNumber;
    protected int levelRecord;

    public Game(Context context, GameView gameView) {
        this.context = context;
        this.gameView = gameView;

        settings = PreferenceManager.getDefaultSharedPreferences(context);
        editor = settings.edit();

        levelNumber = LevelGenerator.currentLevel;
        levelRecord = settings.getInt("level" + levelNumber + "record", Integer.MAX_VALUE);

    }

    public void newGame(){
        int[][] fieldInt = LevelGenerator.getSpecificLevel(LevelGenerator.currentLevel);
        field = new Cell[4][4];

        for (int xx = 0; xx<4; xx++) {
            for (int yy = 0; yy < 4; yy++) {
                field[xx][yy] = new Cell(gameView.cellSize, xx, yy, fieldInt[xx][yy]);
            }
        }

        gameView.turnsCount = 0;

        editor.putBoolean("newGame", true);
        editor.apply();


    }

    protected void checkWin(){
        for (int xx = 0; xx<4; xx++) {
            for (int yy = 0; yy < 4; yy++) {
                if (!( xx+4*yy == field[xx][yy].getValue()-1)){
                    return;
                }
            }
        }
        isWin = true;
        editor.putBoolean("level" + levelNumber + "pass", true);
        if (gameView.turnsCount < settings.getInt("level" + levelNumber + "record", Integer.MAX_VALUE)){
            editor.putInt("level" + levelNumber + "record", gameView.turnsCount);
        }
        editor.apply();
    }
}
