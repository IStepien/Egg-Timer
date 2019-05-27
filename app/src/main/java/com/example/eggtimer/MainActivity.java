package com.example.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.seekBar);

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);

       final TextView timeTextView = findViewById(R.id.timeTextView);

        int maxTime = 600;
        int startingPosition = 0;

        seekBar.setMax(maxTime);
        seekBar.setProgress(startingPosition);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("info", "progress" + progress);
                int minutes = progress/60;
                int seconds = progress-(minutes*60);

                timeTextView.setText(checkDigit(minutes)+":"+checkDigit(seconds));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

}
