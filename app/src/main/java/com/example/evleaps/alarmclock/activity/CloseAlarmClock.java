package com.example.evleaps.alarmclock.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.evleaps.alarmclock.controller.AlarmReceiver;
import com.example.evleaps.alarmclock.controller.Constant;
import com.example.evleaps.alarmclock.R;

import java.util.Calendar;


public class CloseAlarmClock extends AppCompatActivity implements View.OnClickListener {

    private Button        sleep, close;
    private TextView      dateNow;
    private MediaPlayer   mediaPlayer;
    private Calendar      calendar;
    private AlarmManager  alarmManager;
    private PendingIntent pendingIntent;
    private Intent        intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_alarm_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //запуск сигнала
        mediaPlayer = MediaPlayer.create(this, R.raw.boom_boom);
       // mediaPlayer.start();

        sleep   = (Button) findViewById(R.id.sleep_btn);
        close   = (Button) findViewById(R.id.close_btn);
        dateNow = (TextView) findViewById(R.id.date_now);

        sleep.setOnClickListener(this);
        close.setOnClickListener(this);
        dateNow.setOnClickListener(this);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        calendar = Calendar.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
           //     mediaPlayer.stop();
                this.finish();
                break;
            case R.id.sleep_btn:
          //      mediaPlayer.stop();
                pendingIntent = PendingIntent.getBroadcast(CloseAlarmClock.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + Constant.TIME_TO_SLEEP, pendingIntent);
                this.finish();
                break;
            default:
                break;
        }
    }
}
