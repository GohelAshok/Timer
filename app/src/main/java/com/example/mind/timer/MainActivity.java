package com.example.mind.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView timer,countdown;
    Button pause,start,reset,startcountdown;
  /*  int count=0;
    Timer T;*/

    private long startTime = 0;
    private Handler myHandler = new Handler();
    long timeInMillies = 0;
    long timeSwap = 0;
    long finalTime = 0;
    float i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=(TextView)findViewById(R.id.timer);
        countdown=(TextView)findViewById(R.id.countdown);

        startcountdown=(Button)findViewById(R.id.startcountdown);
        pause=(Button)findViewById(R.id.cancel);
        start=(Button)findViewById(R.id.start);
        reset=(Button)findViewById(R.id.reset);

        startcountdown.setOnClickListener(this);
        pause.setOnClickListener(this);
        start.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        // timer count down start
        if (view==startcountdown)
        {
            new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    countdown.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    countdown.setText("done!");
                }
            }.start();
        }

        //counter start

        if (view==start)
        {
           /* T = new Timer();
            T.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timer.setText("count=" + count);
                            count++;

                        }
                    });
                }
            }, 1000, 1000);*/


           if (startTime==0)
           {
                   startTime = SystemClock.uptimeMillis();
                   myHandler.postDelayed(updateTimerMethod, 0);
           }
           else {
               startTime = SystemClock.uptimeMillis();
               myHandler.postDelayed(updateTimerMethod, 0);
           }

        }
        if (view==pause) {
            timeSwap += timeInMillies;
            myHandler.removeCallbacks(updateTimerMethod);
            i=finalTime;
            Log.e("i", String.valueOf(i));

        }
        if (view==reset)
        {
            myHandler.removeCallbacks(updateTimerMethod);
            timer.setText("00:00:000");
            startTime = 0;
            timeInMillies = 0;
            timeSwap = 0;
            finalTime = 0;
        }

    }

    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMillies;

            Log.e("timemillies", String.valueOf(timeInMillies));
            Log.e("finaltime", String.valueOf(timeInMillies));
            Log.e("timeswap", String.valueOf(timeInMillies));

            int seconds = (int) (finalTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            int milliseconds = (int) (finalTime % 1000);
            timer.setText("" + minutes + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", milliseconds));
            myHandler.postDelayed(this, 0);
        }

    };


}

