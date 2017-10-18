package com.example.evleaps.alarmclock.model;

import java.util.Calendar;

/**
 * The POJO alarm clock. Contain context and  main parameters/
 */

public class Alarm {

    private Calendar calendar;
    private String   time;
    private int      ID;


    public Alarm() {
    }

    public Alarm(Calendar calendar, String time, int ID) {
        this.calendar = calendar;
        this.time = time;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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


    @Override
    public String toString() {
        return "Alarm{" +
                "calendar=" + calendar +
                ", time='" + time + '\'' +
                ", ID=" + ID +
                '}';
    }
}
