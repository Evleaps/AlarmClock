package com.example.evleaps.alarmclock.model;

import java.util.Calendar;

/**
 * The POJO alarm clock. Contain context and  main parameters/
 */

public class Alarm {

    private String   time;
    private int      ID;
    private int      IdUser;
    private boolean  state;
    private Calendar calendar;

    public Alarm() {
    }

    public Alarm(String time, int ID, int idUser, boolean state, Calendar calendar) {
        this.calendar = calendar;
        this.time = time;
        this.ID = ID;
        this.IdUser = idUser;
        this.state = state;
    }


    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
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


    @Override
    public String toString() {
        return "Alarm{" +
                "time='" + time + '\'' +
                ", ID=" + ID +
                ", IdUser=" + IdUser +
                ", state=" + state +
                '}';
    }
}
