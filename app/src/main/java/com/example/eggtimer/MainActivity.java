package com.example.eggtimer;


import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView timeTextView;
    SeekBar seekBar;
    Button startButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.timeTextView);
        seekBar = findViewById(R.id.seekBar);

        int maxTime = 600;
        int startingPosition = 0;

        seekBar.setMax(maxTime);
        seekBar.setProgress(startingPosition);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("info", "progress" + progress);
                updateTimer(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        timeTextView.setText(checkDigit(minutes) + ":" + checkDigit(seconds));
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    public void startTimer(View view) {
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        stopButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                startButton.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mediaPlayer.start();
            }
        }.start();
    }

}
