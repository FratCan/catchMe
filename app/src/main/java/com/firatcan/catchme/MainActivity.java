package com.firatcan.catchme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score;
    TextView timeText;
    TextView scoreText;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageViews;

    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        score=0;
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageViews=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,
                imageView6,imageView7,imageView8,imageView9};

        hideView();

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Left: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Out");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageViews) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("Play Again");
                alert.setTitle("Game End/Restart");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish(); //güncel aktivitiye bitirip tekrar başlatacak
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

    }

    public  void increaseScore(View view){
        score+=1;
        scoreText.setText("Score: "+score);
    }
    public void hideView(){
        Random r=new Random();
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageViews) {
                    image.setVisibility(View.INVISIBLE);
                }
                int i=r.nextInt(9);

                imageViews[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,300);
            }
        };

        handler.post(runnable);

    }

}