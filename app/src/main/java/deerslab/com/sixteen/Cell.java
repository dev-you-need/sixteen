package deerslab.com.sixteen;

import android.graphics.Color;
import android.graphics.RectF;

/**
 * Created by keeper on 19.01.2016.
 */
public class Cell{

    private int baseScreenSize;
    private int positionX;
    private int positionY;
    private int coordX;
    private int coordY;
    private int number;
    private int cellSize;
    private int borderSize;
    private RectF rectF;
    private final int INCORRECT_COLOR = Color.rgb(114,216,218);
    private final int CORRECT_COLOR = Color.rgb(250, 159, 3);
    private int boardStartingX;
    private int boardStartingY;


    public Cell(int cellSize, int positionX, int positionY, int number) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.number = number;

        this.cellSize = cellSize;
        borderSize = cellSize/7;

        coordX = borderSize*(positionX+1) + cellSize*positionX;
        coordY = borderSize*(positionY+1) + cellSize*positionY;
        rectF = new RectF(coordX, coordY, coordX+cellSize, coordY+cellSize);
/*
        Log.d("Cell " + positionX + " " +positionY + " baseScreenSize" , baseScreenSize+"");
        Log.d("Cell " + positionX + " " +positionY + " coordX" , coordX+"");
        Log.d("Cell " + positionX + " " +positionY + " coordY" , coordY+"");
*/
    }

    public RectF getRectF() {
        return rectF;
    }

    public int getColor(){
        if (positionX+positionY*4 == number) {
            return CORRECT_COLOR;
        } else {
            return INCORRECT_COLOR;
        }
    }

    public int getValue(){
        return number+1;
    }

    public void updateCellSize(int cellSize, int startingX, int startingY){
        this.cellSize = cellSize;
        boardStartingX=startingX;
        boardStartingY=startingY;
        borderSize = cellSize/7;

        coordX = startingX + borderSize*(positionX+1) + cellSize*positionX;
        coordY = startingY + borderSize*(positionY+1) + cellSize*positionY;
        rectF.set(coordX, coordY, coordX+cellSize, coordY+cellSize);
    }

    public void setNewPosition(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;

        updateCellSize(cellSize, boardStartingX, boardStartingY);

    }

    public void setCentrRect(int x, int y){
        rectF.set(x-cellSize/2, y-cellSize/2,x+cellSize/2, y+cellSize/2);
    }


}
