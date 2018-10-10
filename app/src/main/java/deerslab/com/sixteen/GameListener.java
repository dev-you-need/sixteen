package deerslab.com.sixteen;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by keeper on 20.01.2016.
 */
public class GameListener implements View.OnTouchListener {

    private static final String TAG = GameListener.class.getSimpleName();

    private final GameView gView;
    private int x;
    private int y;

    public GameListener(GameView gView) {
        super();
        this.gView = gView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.d(TAG, event.getAction()+"");

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = (int)event.getX();
                y = (int)event.getY();

                Log.d(TAG, "x="+x+", y="+y);

                if (!gView.game.isWin) {

                    if (iconRevertPress(gView.sXRevertRotation, gView.sYRevertRotation)) {
                        gView.changeClockWise();
                        gView.invalidate();
                    } else {
                        checkArrrowsPress(x, y);
                    }
                } else {
                    gView.context.startActivity(new Intent(gView.context, LevelChooserActivity.class));
                    GameActivity.first.finish();
                }
        }

        return false;
    }

    private boolean iconRevertPress(int sx, int sy){
        return inRange(sx-gView.cellSize, x, sx + gView.cellSize)
                && inRange(sy-gView.cellSize, y, sy + gView.cellSize);
    }

    private void checkArrrowsPress(int sx, int sy){
        for (int xx=0; xx<3; xx++){
            if (inRange(gView.arrowsXs[xx]-gView.arrowSize, sx, gView.arrowsXs[xx]+gView.arrowSize)) {
                for (int yy = 0; yy < 3; yy++) {
                    if (inRange(gView.arrowsYs[yy]-gView.arrowSize, sy, gView.arrowsYs[yy]+gView.arrowSize)){
                        gView.pressArrows(xx, yy);
                    }
                }
            }
        }
    }

    private boolean inRange(float starting, float check, float ending) {
        return (starting <= check && check <= ending);
    }
}
