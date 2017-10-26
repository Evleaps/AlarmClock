package com.example.evleaps.alarmclock.activity.mainActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.evleaps.alarmclock.activity.settings.Hashish;
import com.example.evleaps.alarmclock.controller.AlarmReceiver;
import com.example.evleaps.alarmclock.R;
import com.example.evleaps.alarmclock.controller.LoadUnloadObj;
import com.example.evleaps.alarmclock.model.Alarm;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateAlarmClock extends AppCompatActivity implements View.OnClickListener {

    private Button alarm_on, alarm_off;
    private TimePicker timePicker;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private int hour, minute;
    private Calendar calendar;
    private Intent intent;
    private Alarm alarmClock;
    public static ArrayList<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //объявили виджеты, что-бы удобно было к ним обращаться
        alarm_on = (Button) findViewById(R.id.alarm_on);
        alarm_off = (Button) findViewById(R.id.alarm_off);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        alarmClock = new Alarm();
        alarms = new LoadUnloadObj(this).unload();//восстановил текущее состояние списка
        Log.d("TAG_LOG", "alarms SIZE = " + alarms.size());
        String ss = "";
        for (Alarm s : alarms) {
            ss += s+"\n";
        }
        Log.d("TAG_LOG", "alarms contain: \n " + ss);

        //обработчики
        alarm_on.setOnClickListener(this);
        alarm_off.setOnClickListener(this);

        intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        calendar = Calendar.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_clock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, Hashish.class));
                break;
            case R.id.action_author:
                Toast.makeText(CreateAlarmClock.this, "Автор: Аймалетдинов Р.А.", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_version:
                try {
                    String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                    Toast.makeText(CreateAlarmClock.this, "Версия: " + versionName, Toast.LENGTH_LONG).show();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alarm_on:
                setAlarm_on();
                break;
            case R.id.alarm_off:
                this.finish();
                startActivity(new Intent(this, SelectClock.class));
                break;
            default:
                break;
        }
    }
    //если нажали кнопку Сохранить
    private void setAlarm_on() {
        hour = timePicker.getHour();
        minute = timePicker.getMinute();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        String time = refactorTime();
        alarmClock.setCalendar(calendar);
        alarmClock.setTime(time);
        alarmClock.setState(true);
        try {
            alarmClock.setID(alarms.get(alarms.size()-1).getID()+1);//на 1 больше, чем ID посл эл-а
        } catch (IndexOutOfBoundsException e) {
            alarmClock.setID(1);
            Log.d("LOG_ERROR", "Элементов нет, не найти ID/ присвоен id = 1");
        }

        alarms.add(alarmClock);
        new LoadUnloadObj(this).load(alarms);
        Toast.makeText(this, "Будильник поставлен на " + time, Toast.LENGTH_LONG);


        //установим сигнализацию
        pendingIntent = PendingIntent.getBroadcast(CreateAlarmClock.this, alarmClock.getID() , intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        //возвращаемся, при старте активити приложение восстановит данные из памяти
        Intent intent = new Intent(this, SelectClock.class);
        startActivity(intent);
        finish();
    }


    //добавляю нули, что-бы было не 5.20 утра, а 05.20 утра
    private String refactorTime() {
        String hour_string;
        String minute_string;
        if (hour < 10) {
            hour_string = "0" + hour;
            if (minute < 10) {
                minute_string = "0" + minute;
            } else minute_string = String.valueOf(minute);
        } else {
            hour_string = String.valueOf(hour);
            if (minute < 10) {
                minute_string = "0" + minute;
            } else minute_string = String.valueOf(minute);
        }
        return hour_string + ":" + minute_string;
    }
}