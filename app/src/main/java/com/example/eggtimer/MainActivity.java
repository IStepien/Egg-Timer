package com.example.eggtimer;


import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView timeTextView;
    SeekBar seekBar;

    Button startButton ;
    Button stopButton ;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        timeTextView = findViewById(R.id.timeTextView);
        seekBar = findViewById(R.id.seekBar);

        int maxTime = 600;
        int startingPosition = 0;

        seekBar.setMax(maxTime);
        seekBar.setProgress(startingPosition);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
                startButton.setVisibility(View.VISIBLE);

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
        startButton.setVisibility(View.INVISIBLE);

        seekBar.setEnabled(false);

        stopButton.setVisibility(View.VISIBLE);

        countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mediaPlayer.start();
                seekBar.setEnabled(true);
                seekBar.setProgress(0);
                startButton.setVisibility(View.INVISIBLE);
                stopButton.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
    public void stopTimer(View view){
        timeTextView.setText("00:00");
        seekBar.setProgress(0);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        startButton.setVisibility(View.VISIBLE);

    }

}
