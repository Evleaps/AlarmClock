package com.example.evleaps.alarmclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CloseAlarmClock extends AppCompatActivity implements View.OnClickListener {

    Button   sleep;
    Button   close;
    TextView dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_alarm_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sleep   = (Button) findViewById(R.id.sleep_btn);
        close   = (Button) findViewById(R.id.close_btn);
        dateNow = (TextView) findViewById(R.id.date_now);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.close_btn:


        }
    }
}
