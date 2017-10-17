package com.example.evleaps.alarmclock.model;

import android.content.Context;

import java.util.Calendar;

/**
 * The POJO alarm clock. Contain context and  main parameters/
 */

public class Alarm {

    private Calendar calendar;
    private Context  context;
    private String   time;



    public Alarm() {
    }

    public Alarm(Calendar calendar, Context context, String time) {
        this.calendar = calendar;
        this.context = context;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "calendar=" + calendar +
                ", context=" + context +
                '}';
    }
}
