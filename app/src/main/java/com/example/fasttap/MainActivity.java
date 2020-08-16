package com.example.fasttap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

     int k = 0;
    int a=0;
    TextView countdownttext;
    CountDownTimer countDownTimer;
    long TimeLeft = 10000;
    boolean TimeRuning;
    MediaPlayer mp;
    int end=0;
    int BEST=0;

    TextView Bscore;
    TextView score;
    Button btn;
    Button btn_re;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
          mp = MediaPlayer.create(this, R.raw.sound);

        Bscore = findViewById(R.id.bests);
        score = findViewById(R.id.scr);
        btn = findViewById(R.id.button);
        btn_re = findViewById(R.id.btn);
        countdownttext = findViewById(R.id.count);


        btn.setOnClickListener(this);
        btn_re.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

               if (end==0)k++;

                score.setText(Integer.toString(k));
                if (a == 0) {
                    startTimer();
                    mp.start();
                }


                break;
            case R.id.btn:
                k = 0;
                score.setText(Integer.toString(k));

                stoptimer();



                break;


        }
    }



    public void startTimer() {
        countDownTimer = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long l) {
                TimeLeft = l;
                a=1;
                UpdateTimer();

            }

            @Override
            public void onFinish() {
                end=1;
                if (k>BEST) {
                    BEST=k;
                    Bscore.setText(Integer.toString(k));
                }else{
                    Bscore.setText(Integer.toString(BEST));
                }
                Intent i = new Intent(MainActivity.this, ScoreBoard.class);
                i.putExtra(EXTRA_NUMBER, BEST);

                startActivity(i);


            }

        }.start();

        TimeRuning = true;
    }


    public void stoptimer() {

        countDownTimer.cancel();
        TimeRuning = false;
        TimeLeft = 10000;
        a=0;
        end=0;
        UpdateTimer();
    }

    public void UpdateTimer() {
        int m = (int) TimeLeft / 60000;
        int s = (int) TimeLeft % 60000 / 1000;
        String timelefttext;
        timelefttext = "" + m;
        timelefttext += ":";
        if (s < 10) {
            timelefttext += "0";
        }
        timelefttext += s;
        countdownttext.setText(timelefttext);






    }
    


}


