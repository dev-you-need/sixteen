package deerslab.com.sixteen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by keeper on 22.01.2016.
 */
public class LevelChooserActivity extends Activity{

    private SharedPreferences preferences;
    private boolean level1pass, level2pass, level3pass, level4pass, level5pass, level6pass, level7pass, level8pass, level9pass;
    private Typeface font;
    private Button level1, level2, level3,level4, level5, level6, level7, level8, level9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        level1pass = settings.getBoolean("level1pass", false);
        level2pass = settings.getBoolean("level2pass", false);
        level3pass = settings.getBoolean("level3pass", false);
        level4pass = settings.getBoolean("level4pass", false);
        level5pass = settings.getBoolean("level5pass", false);
        level6pass = settings.getBoolean("level6pass", false);
        level7pass = settings.getBoolean("level7pass", false);
        level8pass = settings.getBoolean("level8pass", false);
        level9pass = settings.getBoolean("level9pass", false);

        Resources resources = getResources();
        font = Typeface.createFromAsset(resources.getAssets(), "font.ttf");

        setContentView(R.layout.level_chooser_activity);

        TextView textChooseLevel = (TextView) findViewById(R.id.textChooseLevel);
        textChooseLevel.setTypeface(font);

        level1 = (Button) findViewById(R.id.idbtnLevel1);
        if (level1pass) level1.setBackgroundResource(R.drawable.gold_button_rectangle);
        level1.setTypeface(font);
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 1;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level2 = (Button) findViewById(R.id.idbtnLevel2);
        if (level2pass) level2.setBackgroundResource(R.drawable.gold_button_rectangle);
        level2.setTypeface(font);
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 2;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level3 = (Button) findViewById(R.id.idbtnLevel3);
        if (level3pass) level3.setBackgroundResource(R.drawable.gold_button_rectangle);
        level3.setTypeface(font);
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 3;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level4 = (Button) findViewById(R.id.idbtnLevel4);
        if (level4pass) level4.setBackgroundResource(R.drawable.gold_button_rectangle);
        level4.setTypeface(font);
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 4;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level5 = (Button) findViewById(R.id.idbtnLevel5);
        if (level5pass) level5.setBackgroundResource(R.drawable.gold_button_rectangle);
        level5.setTypeface(font);
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 5;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level6 = (Button) findViewById(R.id.idbtnLevel6);
        if (level6pass) level6.setBackgroundResource(R.drawable.gold_button_rectangle);
        level6.setTypeface(font);
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 6;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level7 = (Button) findViewById(R.id.idbtnLevel7);
        if (level7pass) level7.setBackgroundResource(R.drawable.gold_button_rectangle);
        level7.setTypeface(font);
        level7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 7;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level8 = (Button) findViewById(R.id.idbtnLevel8);
        if (level8pass) level8.setBackgroundResource(R.drawable.gold_button_rectangle);
        level8.setTypeface(font);
        level8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 8;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

        level9 = (Button) findViewById(R.id.idbtnLevel9);
        if (level9pass) level9.setBackgroundResource(R.drawable.gold_button_rectangle);
        level9.setTypeface(font);
        level9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LevelGenerator.currentLevel = 9;
                startActivity(new Intent(LevelChooserActivity.this, GameActivity.class));
                finish();
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        level1pass = settings.getBoolean("level1pass", false);
        level2pass = settings.getBoolean("level2pass", false);
        level3pass = settings.getBoolean("level3pass", false);
        level4pass = settings.getBoolean("level4pass", false);
        level5pass = settings.getBoolean("level5pass", false);
        level6pass = settings.getBoolean("level6pass", false);
        level7pass = settings.getBoolean("level7pass", false);
        level8pass = settings.getBoolean("level8pass", false);
        level9pass = settings.getBoolean("level9pass", false);

        if (level1pass) level1.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level2pass) level2.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level3pass) level3.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level4pass) level4.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level5pass) level5.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level6pass) level6.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level7pass) level7.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level8pass) level8.setBackgroundResource(R.drawable.gold_button_rectangle);
        if (level9pass) level9.setBackgroundResource(R.drawable.gold_button_rectangle);
    }

}
