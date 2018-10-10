package deerslab.com.sixteen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * Created by keeper on 19.01.2016.
 */
public class GameView extends View {
    //Internal Constants
    private static final String TAG = GameView.class.getSimpleName();
    private final long ANIMATIONTIME = 400000000;

    //Internal variables
    private final Paint paint = new Paint();
    private boolean clockwise = true;
    protected boolean hasSaveState = false;
    protected int turnsCount;

    //animation kostyls
    private long animationStartTime;
    private boolean animation;
    private int pressedArrowPosX;
    private int pressedArrowPosY;

    //layouts
    private Typeface font;
    private int screenMiddleX;
    private int screenMiddleY;
    private int baseScreenSize;
    protected int cellSize;
    protected int arrowSize;
    private int borderSize;
    private int startingX;
    private int startingY;
    private int endingX;
    private int endingY;
    protected int sXRevertRotation;
    protected int sYRevertRotation;
    protected int arrowsXs[] = new int[3];
    protected int arrowsYs[] = new int[3];
    private int width;
    private int height;
    //Text layouts
    private float gameOverTextSize;
    private float textSize;
    private float bodyTextSize;

    protected Context context;

    Bitmap bitmapCell;
    Bitmap arrowCW;
    Bitmap arrowCCW;
    Bitmap arrowRevert;

    public Game game;

    private BitmapDrawable winGameOverlay;
    private Drawable lightUpRectangle;
    private Drawable backgroundRectangle;

    public GameView(Context context) {
        super(context);
        this.context = context;

        Resources resources = context.getResources();
        font = Typeface.createFromAsset(resources.getAssets(), "font.ttf");
        paint.setTypeface(font);
        paint.setAntiAlias(true);

        lightUpRectangle = resources.getDrawable(R.drawable.light_up_rectangle);
        backgroundRectangle = resources.getDrawable(R.drawable.background_rectangle);
        bitmapCell = BitmapFactory.decodeResource(resources, R.drawable.button);
        arrowCW = BitmapFactory.decodeResource(resources, R.drawable.rotate_green);
        arrowCCW = BitmapFactory.decodeResource(resources, R.drawable.rotate_blue);
        arrowRevert = BitmapFactory.decodeResource(resources, R.drawable.rotate_revert);

        game = new Game(context, this);
        setOnTouchListener(new GameListener(this));
        game.newGame();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawBackgrounds(canvas);

        if (animation) {
            animationCells();
        }
        drawCells(canvas);

        if (!game.isWin){
            drawArrows(canvas);
        }

        drawIcons(canvas);
        drawScoreText(canvas);

        if (game.isWin){
            drawEndGameState(canvas);
            invalidate();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        getLayout(w, h);
        createOverlays();

    }

    private void getLayout(int width, int height){
        this.width = width;
        this.height = height;
        baseScreenSize = Math.min(width, height);
        cellSize = Math.min(width / (5), height / (7));
        screenMiddleX = width / 2;
        screenMiddleY = height / 2;
        int boardMiddleY = screenMiddleY + cellSize / 2;
        arrowSize = cellSize/2;
        borderSize = cellSize/7;

        startingX = (screenMiddleX - (cellSize + borderSize) * 2 - borderSize / 2);
        endingX = (screenMiddleX + (cellSize + borderSize) * 2 + borderSize / 2);
        startingY = (boardMiddleY - (cellSize + borderSize) * 2 - borderSize / 2);
        endingY = (boardMiddleY + (cellSize + borderSize) * 2 + borderSize / 2);

        updateCellSize(cellSize, startingX, startingY);

        if (width < height){
            sXRevertRotation = screenMiddleX;
            sYRevertRotation = endingY + cellSize/2;
        } else {
            sXRevertRotation = endingX + cellSize/2;
            sYRevertRotation = screenMiddleY + (cellSize + borderSize)/2;
        }

        //Text dimensions
        paint.setTextSize(cellSize);

        bodyTextSize = cellSize/3;
        textSize = cellSize/4;

        float widthWithPadding = endingX - startingX;

        gameOverTextSize = cellSize;

    }

    private void drawCells(Canvas canvas){

        for (int xx = 0; xx<4; xx++){
            for (int yy = 0; yy<4; yy++){
                Cell currentCell = game.field[xx][yy];
                paint.setColor(currentCell.getColor());
                canvas.drawRoundRect(currentCell.getRectF(), baseScreenSize / 20, baseScreenSize / 20, paint);

                paint.setColor(Color.WHITE);
                paint.setTextSize(cellSize);
                paint.setTextAlign(Paint.Align.CENTER);
                int textShiftY = centerText();
                canvas.drawText(""+currentCell.getValue(), currentCell.getRectF().centerX(), currentCell.getRectF().centerY() - textShiftY, paint);

            }
        }
    }

    private void drawArrows(Canvas canvas){

        for (int xx = 0; xx<3; xx++) {
            int centrX = startingX+(borderSize+cellSize)*(xx+1)+borderSize/2;
            arrowsXs[xx] = centrX;
            for (int yy = 0; yy < 3; yy++) {
                int centrY = startingY + (borderSize+cellSize)*(yy+1)+borderSize/2;
                arrowsYs[yy] = centrY;
                Rect rect = new Rect(centrX-arrowSize/2, centrY-arrowSize/2, centrX+arrowSize/2, centrY+arrowSize/2);
                canvas.drawBitmap(clockwise ? arrowCW : arrowCCW, null, rect, paint);
            }
        }
    }

    private void drawBackgrounds(Canvas canvas){
        canvas.drawARGB(80, 102, 204, 255);
    }

    private int centerText() {
        return (int) ((paint.descent() + paint.ascent()) / 2);
    }

    public int getBaseScreenSize() {
        Log.d("GameView baseScreenSize", ""+baseScreenSize);
        return baseScreenSize;
    }

    private void updateCellSize(int cellSize, int startingX, int startingY){
        for (int xx = 0; xx<4; xx++) {
            for (int yy = 0; yy < 4; yy++) {
                game.field[xx][yy].updateCellSize(cellSize, startingX, startingY);
            }
        }
    }

    private void drawIcons(Canvas canvas){
        Rect rect = new Rect(sXRevertRotation-cellSize/2, sYRevertRotation-cellSize/2, sXRevertRotation+cellSize/2, sYRevertRotation+cellSize/2);
        canvas.drawBitmap(arrowRevert, null, rect, paint);

    }

    public void changeClockWise(){
        clockwise = !clockwise;
        invalidate();
    }

    public void pressArrows(int posX, int posY){
        Log.d(TAG+"pressArrows", "posX="+posX + ", posY=" + posY);
        animation = true;
        animationStartTime = System.nanoTime();
        pressedArrowPosX = posX;
        pressedArrowPosY = posY;
        Cell buff = game.field[posX][posY];

        if (clockwise){

            game.field[posX][posY] = game.field[posX][posY+1];
            game.field[posX][posY+1] = game.field[posX+1][posY+1];
            game.field[posX+1][posY+1] = game.field[posX+1][posY];
            game.field[posX+1][posY] = buff;


        } else {

            game.field[posX][posY] = game.field[posX+1][posY];
            game.field[posX+1][posY] = game.field[posX+1][posY+1];
            game.field[posX+1][posY+1] = game.field[posX][posY+1];
            game.field[posX][posY+1] = buff;

        }
        turnsCount++;
        invalidate();

        game.checkWin();

    }

    private void animationCells(){
        long currentTime= System.nanoTime();
        if (currentTime > animationStartTime + ANIMATIONTIME){
            animation = false;
            for (int xx = 0; xx<4; xx++) {
                for (int yy = 0; yy < 4; yy++) {
                    game.field[xx][yy].setNewPosition(xx, yy);
                }
            }

        } else {

            double radius = Math.sqrt(2)*(cellSize+borderSize)/2;
            double shift = (((double)(currentTime - animationStartTime))/ANIMATIONTIME)*Math.PI/2;

            if (clockwise){
                game.field[pressedArrowPosX][pressedArrowPosY].setCentrRect(arrowsXs[pressedArrowPosX]+(int)(Math.sin((-1*Math.PI/4)-shift)*radius), arrowsYs[pressedArrowPosY] + (int)(Math.cos((-1*Math.PI/4)-shift)*radius));
                game.field[pressedArrowPosX+1][pressedArrowPosY].setCentrRect(arrowsXs[pressedArrowPosX]+(int)(Math.sin((5*Math.PI/4)-shift)*radius), arrowsYs[pressedArrowPosY] + (int)(Math.cos((5*Math.PI/4)-shift)*radius));
                game.field[pressedArrowPosX+1][pressedArrowPosY+1].setCentrRect(arrowsXs[pressedArrowPosX]+(int)(Math.sin((3*Math.PI/4)-shift)*radius), arrowsYs[pressedArrowPosY] + (int)(Math.cos((3*Math.PI/4)-shift)*radius));
                game.field[pressedArrowPosX][pressedArrowPosY+1].setCentrRect(arrowsXs[pressedArrowPosX] + (int) (Math.sin((Math.PI / 4) - shift) * radius), arrowsYs[pressedArrowPosY] + (int) (Math.cos((Math.PI / 4) - shift) * radius));

            } else {
                game.field[pressedArrowPosX][pressedArrowPosY].setCentrRect(arrowsXs[pressedArrowPosX]+(int)(Math.sin((3*Math.PI/4)+shift)*radius), arrowsYs[pressedArrowPosY] + (int)(Math.cos((3*Math.PI/4)+shift)*radius));
                game.field[pressedArrowPosX+1][pressedArrowPosY].setCentrRect(arrowsXs[pressedArrowPosX]+(int)(Math.sin((1*Math.PI/4)+shift)*radius), arrowsYs[pressedArrowPosY] + (int)(Math.cos((1*Math.PI/4)+shift)*radius));
                game.field[pressedArrowPosX+1][pressedArrowPosY+1].setCentrRect(arrowsXs[pressedArrowPosX]+(int)(Math.sin((-1*Math.PI/4)+shift)*radius), arrowsYs[pressedArrowPosY] + (int)(Math.cos((-1*Math.PI/4)+shift)*radius));
                game.field[pressedArrowPosX][pressedArrowPosY+1].setCentrRect(arrowsXs[pressedArrowPosX] + (int) (Math.sin((5*Math.PI / 4) + shift) * radius), arrowsYs[pressedArrowPosY] + (int) (Math.cos((5*Math.PI / 4) + shift) * radius));

            }

        }
        invalidate();

    }

    private void createOverlays() {
        Resources resources = getResources();
        //Initialize overlays
        Bitmap bitmap = Bitmap.createBitmap(endingX - startingX, endingY - startingY, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        createEndGameStates(canvas, true);
        winGameOverlay = new BitmapDrawable(resources, bitmap);

    }

    private void createEndGameStates(Canvas canvas, boolean win){
        int width = endingX - startingX;
        int length = endingY - startingY;
        int middleX = width / 2;
        int middleY = length / 2;

        lightUpRectangle.setAlpha(200);
        drawDrawable(canvas, lightUpRectangle, 0, 0, width, length);
        lightUpRectangle.setAlpha(255);
        paint.setColor(Color.WHITE);
        paint.setAlpha(255);
        paint.setTextSize(gameOverTextSize);
        paint.setTextAlign(Paint.Align.CENTER);
        int textBottom = middleY - centerText();
        canvas.drawText(getResources().getString(R.string.YouWin), middleX, textBottom, paint);
        paint.setTextSize(textSize);
        paint.setColor(Color.WHITE);
        canvas.drawText(getResources().getString(R.string.WinGameTextPart1), middleX, textBottom + cellSize*1/2, paint);
        canvas.drawText(getResources().getString(R.string.WinGameTextPart2), middleX, textBottom+cellSize*3/4, paint);


    }

    private void drawEndGameState(Canvas canvas) {
        double alphaChange = 1;
        BitmapDrawable displayOverlay = winGameOverlay;

        //displayOverlay.setBounds(startingX, startingY, endingX, endingY);
        displayOverlay.setBounds(0, 0, width, height);
        displayOverlay.setAlpha((int) (255 * alphaChange));
        displayOverlay.draw(canvas);
    }

    private void drawDrawable(Canvas canvas, Drawable draw, int startingX, int startingY, int endingX, int endingY) {
        draw.setBounds(startingX, startingY, endingX, endingY);
        draw.draw(canvas);
    }

    private void drawScoreText(Canvas canvas){
        backgroundRectangle.setBounds(startingX + borderSize, startingX + borderSize, screenMiddleX - borderSize / 2, startingY - borderSize);
        backgroundRectangle.draw(canvas);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(bodyTextSize);
        paint.setColor(Color.WHITE);
        int xText = (startingX+screenMiddleX)/2;
        int y1Text = cellSize;
        canvas.drawText(getResources().getString(R.string.Turns)+ " " + turnsCount, xText, y1Text, paint);
        int y2Text = (int)(1.5*cellSize);
        String recordText = ((game.levelRecord < Integer.MAX_VALUE) ? "" + game.levelRecord : "-");
        canvas.drawText(getResources().getString(R.string.Record)+ " " + recordText, xText, y2Text, paint);
    }

}
