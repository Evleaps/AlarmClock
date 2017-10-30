package com.example.evleaps.alarmclock.controller;
import com.example.evleaps.alarmclock.model.Alarm;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Клаас возвращает календарь. Используется в:
 * {@link com.example.evleaps.alarmclock.activity.mainActivity.CreateAlarmClock}
 * {@link BoxAdapter}
 *
 * Если сейчас 15.00 а будильник я поставил на 14.00, сигнализацию ставим на след день
 */

public class SetAlarmManager {
    private Alarm alarm;

    public SetAlarmManager(Alarm alarm) {
        this.alarm = alarm;
    }

    public Calendar returnCalendar() {
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = Integer.parseInt(alarm.getTime().split(":")[0]);
        int minutes = Integer.parseInt(alarm.getTime().split(":")[1]);

        //если сейчас позднее чем дата будильника, ставим на след день
        if (calendar.get(Calendar.HOUR_OF_DAY) > hour) {
            calendar.set(year, month, day + 1, hour, minutes, 0);
        } else if (calendar.get(Calendar.HOUR_OF_DAY) == hour) {
            //если равно текущему часу, проверим минуты
            if (calendar.get(Calendar.MINUTE) >= minutes)
                calendar.set(year, month, day + 1, hour, minutes, 0);
            else
                calendar.set(year, month, day, hour, minutes, 0);
        } else {
            calendar.set(year, month, day, hour, minutes, 0);
        }

        return calendar;
    }
}

