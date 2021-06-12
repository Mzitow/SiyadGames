package my_puzzles;

import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mzitow.games.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 *
 *
 * @author Ismail, Qadar and Siyad
 */

public class PuzzleActivity extends AppCompatActivity implements Runnable, View.OnTouchListener {

    PuzzleLayout puzzleLayout;
    private ViewDragHelper viewDragHelper;
    TextView tvTips, timer_tv, score_tv ;
    ImageView ivTips;
    private  int remaning_time = 120;
    private CountDownTimer countDownTimer;
    int squareRootNum = 2;
    int drawableId = R.mipmap.pic_02;
    DataHelper dataHelper;
    int counter;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);



        ivTips = (ImageView) findViewById(R.id.iv_tips);
        ivTips.setImageResource(drawableId);
        tvTips = (TextView) findViewById(R.id.tv_tips);
        timer_tv = findViewById(R.id.timer);
        score_tv = findViewById(R.id.score);


        tvTips.setOnTouchListener(PuzzleActivity.this);


        puzzleLayout = (PuzzleLayout) findViewById(R.id.activity_swipe_card);
        puzzleLayout.setImage(drawableId, squareRootNum);
       // Log.d(TAG, "scrolling " + scrolls);
       //



        timer();


      //


        puzzleLayout.setOnCompleteCallback(new PuzzleLayout.OnCompleteCallback() {

            @Override
            public void onComplete() {


                Toast.makeText(PuzzleActivity.this, R.string.next, Toast.LENGTH_LONG).show();
                puzzleLayout.postDelayed(PuzzleActivity.this, 800);
            //  scores++;






            }
        });


    }
//    public void computeMoves(){
//        if ( viewDragHelper.getViewDragState()!= 0){
//            counter++;
//            score_tv.setText(String.valueOf(counter));
//
//
//
//        }
//
//    }

    @Override
    public void run() {
        squareRootNum++;
        drawableId++;



        if(squareRootNum > 10){
            Toast.makeText(PuzzleActivity.this, R.string.complete, Toast.LENGTH_SHORT).show();
            showDialog();

        }else {

            ivTips.setImageResource(drawableId);
            puzzleLayout.setImage(drawableId, squareRootNum);






        }

    }



    private void showDialog() {
        new AlertDialog.Builder(PuzzleActivity.this)
                .setTitle(R.string.success)
                .setMessage(R.string.restart)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                squareRootNum = 2;
                                drawableId = R.mipmap.pic_02;
                                ivTips.setImageResource(drawableId);
                                puzzleLayout.setImage(drawableId, squareRootNum);
                               // scores = 0;

                            }
                        }).setNegativeButton(R.string.exit,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ivTips.setVisibility(View.VISIBLE);
                break;
            default:
                ivTips.setVisibility(View.GONE);
        }
        return true;
    }
    public void timer(){
        countDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remaning_time =(int) millisUntilFinished / 1000;
                timer_tv.setText(String.valueOf(remaning_time));


            }

            @Override
            public void onFinish() {
                AlertDialog alertDialog=  new AlertDialog.Builder(PuzzleActivity.this)
                        .setTitle("Finished")
                        .setMessage(R.string.restart)
                        .setPositiveButton(R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        squareRootNum = 2;
                                        drawableId = R.mipmap.pic_02;
                                        ivTips.setImageResource(drawableId);
                                        puzzleLayout.setImage(drawableId, squareRootNum);
                                        countDownTimer.start();
                                      //  scores = 0;
                                    }
                                }).setNegativeButton(R.string.exit,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }).show();
                alertDialog.setCanceledOnTouchOutside(false);

            }
        };
        countDownTimer.start();

    }
}
