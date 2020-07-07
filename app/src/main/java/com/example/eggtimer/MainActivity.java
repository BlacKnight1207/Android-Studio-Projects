package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timer;
    SeekBar sb;
    MediaPlayer m;
    CountDownTimer cnt;
    Button b;
    boolean counter = false;
    public void resetTimer(){
        timer.setText("00:30");
        sb.setProgress(30);
        sb.setEnabled(true);
        cnt.cancel();
        b.setText("Go!");
        counter = false;
        
    }
    public void onClick(View view){
        if(counter){
          resetTimer();
        }
        else {
            counter = true;
            sb.setEnabled(false);
            b.setText("Stop");
            Log.i("Info", "Button Clicked");
            cnt = new CountDownTimer(sb.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer(((int) l / 1000));
                }

                @Override
                public void onFinish() {
                    m = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    m.start();
                    resetTimer();
                }
            }.start();
        }
    }
         public void updateTimer(int i){
             int minutes = i/60 ;
             int sec = i - (minutes*60) ;
             String minuteString = Integer.toString(minutes);
             String secString = Integer.toString(sec);
             if(sec <= 9){
                 secString = "0" + secString;
             }
             if(minutes <= 9){
                 minuteString = "0" + minuteString;
             }
             timer.setText(minuteString + ":" + secString);
         }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb =  findViewById(R.id.seekBar);
        timer  = findViewById(R.id.textView2);
        b = findViewById(R.id.button4);
        sb.setMax(600);
        sb.setProgress(30);
    
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               updateTimer(i);
              
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}