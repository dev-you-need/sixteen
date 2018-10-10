package deerslab.com.sixteen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainMenuActivity extends Activity {

    private LinearLayout layout;
    private int baseScreenSize;
    private boolean hasSaveState;
    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        Resources resources = getResources();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        hasSaveState = settings.getBoolean("save_state", false);

        font = Typeface.createFromAsset(resources.getAssets(), "font.ttf");


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);

        Button btnNewGame = (Button)findViewById(R.id.btnidNewGame);
        btnNewGame.setText(R.string.NewGame);
        btnNewGame.setTypeface(font);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 1;
                startActivity(new Intent(MainMenuActivity.this, LevelChooserActivity.class));
            }
        });

        Button btnRandomLevel = (Button)findViewById(R.id.btnidRandomLevel);
        btnRandomLevel.setText(R.string.RandomLevel);
        btnRandomLevel.setTypeface(font);
        btnRandomLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 0;
                startActivity(new Intent(MainMenuActivity.this, GameActivity.class));
            }
        });

        Button btnExit = (Button)findViewById(R.id.btnidExit);

        btnExit.setText(R.string.Exit);
        btnExit.setTypeface(font);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
