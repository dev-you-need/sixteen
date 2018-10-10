package deerslab.com.sixteen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by keeper on 21.01.2016.
 */
public class LevelGenerator {

    private static int level[][]= new int[4][4];
    protected static int currentLevel;

    private static int[][] getRandomLevel(){

        ArrayList<Integer> usedValues = new ArrayList<>();
        int currentValue;
        Random random = new Random();
        for (int x=0; x<4; x++){
            for (int y=0; y<4; y++){
                while (usedValues.contains(currentValue=random.nextInt(16))){

                }
                usedValues.add(currentValue);
                level[x][y] = currentValue;
            }
        }

        return level;
    }

    public static int [][] getSpecificLevel(int levelNumber){

        switch (levelNumber){
            case 1:
                level[0] = new int[]{4, 5, 8, 12};
                level[1] = new int[]{0, 1, 9, 13};
                level[2] = new int[]{2, 6, 11, 10};
                level[3] = new int[]{3, 7, 15, 14};
                return level;
            case 2:
                level[0] = new int[]{4, 5, 8, 12};
                level[1] = new int[]{0, 11, 6, 13};
                level[2] = new int[]{2, 9, 1, 10};
                level[3] = new int[]{3, 7, 15, 14};
                return level;
            case 3:
                level[0] = new int[]{0, 1, 13, 12};
                level[1] = new int[]{4, 5, 9, 8};
                level[2] = new int[]{7, 6, 10, 11};
                level[3] = new int[]{3, 2, 14, 15};
                return level;
            case 4:
                level[0] = new int[]{4, 1, 12, 13};
                level[1] = new int[]{0, 5, 9, 8};
                level[2] = new int[]{7, 6, 10, 15};
                level[3] = new int[]{2, 3, 14, 11};
                return level;
            case 5:
                level[0] = new int[]{0, 4, 8, 12};
                level[1] = new int[]{1, 5, 9, 13};
                level[2] = new int[]{2, 6, 10, 15};
                level[3] = new int[]{3, 7, 11, 14};
                return level;
            case 6:
                level[0] = new int[]{1, 4, 8, 12};
                level[1] = new int[]{0, 5, 9, 13};
                level[2] = new int[]{2, 6, 10, 15};
                level[3] = new int[]{3, 7, 11, 14};
                return level;
            case 7:
                level[0] = new int[]{5, 4, 8, 9};
                level[1] = new int[]{1, 0, 12, 13};
                level[2] = new int[]{2, 3, 15, 14};
                level[3] = new int[]{6, 7, 11, 10};
                return level;
            case 8:
                level[0] = new int[]{0, 4, 9, 12};
                level[1] = new int[]{5, 1, 8, 13};
                level[2] = new int[]{2, 7, 14, 10};
                level[3] = new int[]{3, 6, 11, 15};
                return level;
            case 9:
                level[0] = new int[]{15, 11, 7, 3};
                level[1] = new int[]{14, 10, 6, 2};
                level[2] = new int[]{13, 9, 5, 1};
                level[3] = new int[]{12, 8, 4, 0};
                return level;

            default:
                return getRandomLevel();

        }
    }
}
