package com.example.evleaps.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class CloseAlarmClock extends AppCompatActivity implements View.OnClickListener {

    Button       sleep;
    Button       close;
    TextView     dateNow;
    MediaPlayer  mediaPlayer;
    Calendar     calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_alarm_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //запуск сигнала
        mediaPlayer = MediaPlayer.create(this, R.raw.boom_boom);
        mediaPlayer.start();

        sleep   = (Button) findViewById(R.id.sleep_btn);
        close   = (Button) findViewById(R.id.close_btn);
        dateNow = (TextView) findViewById(R.id.date_now);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.close_btn:
                mediaPlayer.stop();
                break;
            case R.id.sleep_btn:
                mediaPlayer.stop();
                calendar = Calendar.getInstance();
                calendar.set(Calendar.MINUTE, (int) (new Date().getTime() + Constant.TIME_TO_SLEEP));
                new AlarmClock().setAlarmTime(calendar, true);

        }
    }
}
