package my_puzzles;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mzitow.games.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

import my_puzzles.model.Block;


class DataHelper extends  AppCompatActivity {

    static final int N = -1;
    static final int L = 0;
    static final int T = 1;
    static final int R = 2;
    static final int B = 3;

  //  static   int sum=0;






    private static final String TAG = DataHelper.class.getSimpleName();

    private int squareRootNum;
    private List<Block> models;
  //  ArrayList<Integer> moves= new ArrayList<>();






    DataHelper(){
        models = new ArrayList<>();
    }



    private void reset() {
        models.clear();
        int position = 0;

        for (int i = 0; i< squareRootNum; i++){
            for (int j = 0; j < squareRootNum; j++){
                models.add(new Block(position, i, j));
                position ++;

            }
        }
    }

    void setSquareRootNum(int squareRootNum){
        this.squareRootNum = squareRootNum;
        reset();
    }

    /**
     * Exchange the value of the indexed model with the value of the blank model.
     */


    boolean swapValueWithInvisibleModel(int index){
     //   Log.d(TAG, "swapValueWithInvisibleModel: " + index);



        Block formModel = models.get(index);
        Block invisibleModel = models.get(0);
        swapValue(formModel, invisibleModel);

        return isCompleted();
    }
    /**
     * Exchange the values ​​of the two models
     */



    private void swapValue(Block model, Block invisibleModel) {

        int position = model.position;
        int hPosition = model.hPosition;
        int vPosition = model.vPosition;

        model.position = invisibleModel.position;
        model.hPosition = invisibleModel.hPosition;
        model.vPosition = invisibleModel.vPosition;

        invisibleModel.position = position;
        invisibleModel.hPosition = hPosition;
        invisibleModel.vPosition = vPosition;
//        int [] moves= {position};
//        for (int moving: moves){
//            ++sum;
//        }
    }
   // static  int scores = sum;

    /**
     * Determine whether the puzzle is completed.
     */



    private boolean isCompleted(){

     
        int num = squareRootNum * squareRootNum;
        for (int i = 0; i < num; i++){
         //  Log.d(TAG, "isnotCompleted: " + i );

            Block model = models.get(i);
        //   Log.d(TAG, "isCompleted: " + i);

            if(model.position != i){
               // Log.d(TAG, "isnotCompleted: " + i );

                return false;

            }
        }

        return true;


    }





    public Block getModel(int index){
        return models.get(index);
    }


    /**
     * Get the index of the model by the given location
     */

    private int getIndexByCurrentPosition(int currentPosition){
        int num = squareRootNum * squareRootNum;
        for (int i = 0; i < num; i++) {

          //  Log.d(TAG, "model" + i);



            if(models.get(i).position == currentPosition)

                return i;
        }
        return -1;
    }
  //  ArrayList moving = moves;

    /**
     * Randomly query the index of a model around the blank position.
     */


    public int findNeighborIndexOfInvisibleModel() {
        Block invisibleModel = models.get(0);
        int position = invisibleModel.position;
        int x = position % squareRootNum;
        int y = position / squareRootNum;
        int direction = new Random(System.nanoTime()).nextInt(4);

        switch (direction){
            case L:

                if(x != 0)
                    return getIndexByCurrentPosition(position - 1);


            case T:
                if(y != 0)

                    return getIndexByCurrentPosition(position - squareRootNum);

            case R:

                if(x != squareRootNum - 1)
                  //  Log.d(TAG, "direction " + direction);

                    return getIndexByCurrentPosition(position + 1);
            case B:

                if(y != squareRootNum - 1)

                    return getIndexByCurrentPosition(position + squareRootNum);
        }

        return findNeighborIndexOfInvisibleModel() ;

    }

    /**
     * Get the movable direction of the model at the index, and return -1 if it cannot be moved.
     */


    int getScrollDirection(int index){

        Block model = models.get(index);
        int position = model.position;

        //Get the coordinates x y of the current view location


        int x = position % squareRootNum;
        int y = position / squareRootNum;
        int invisibleModelPosition = models.get(0).position;

        /*
         * Determine whether the current position can be moved, and if it can be moved, return the movable direction.
         */

//
//      for (int i = 0; i<position; i++){
//          Log.d(TAG, "getscroll " + i);
//
//
//      }


        if(x != 0 && invisibleModelPosition == position - 1){
           // Log.d(TAG, "left " );
            return L;}

        if(x != squareRootNum - 1 && invisibleModelPosition == position + 1){
           // Log.d(TAG, "right " );
            return R;}

        if(y != 0 && invisibleModelPosition == position - squareRootNum){
           // Log.d(TAG, "top ");
            return T;}

        if(y != squareRootNum - 1 && invisibleModelPosition == position + squareRootNum){
          //  Log.d(TAG, "bottom " );
            return B;}

        return N;

    }


}
