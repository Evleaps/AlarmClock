package com.example.evleaps.alarmclock.controller;

import com.example.evleaps.alarmclock.activity.SelectClock;
import com.example.evleaps.alarmclock.model.Alarm;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Constant for {@link SelectClock}
 */

public class Constant {
    public static Set<String>  setAlarm = new LinkedHashSet<>();
    public static final String SAVE_DATE_CLOCK = "save_date_newAlarmClock";
    public static final String KEY_LOAD_JBJ_IN_SPREF = "save_obj";
    public static final String KEY_UNLOAD_JBJ_IN_SPREF = "unload_obj";
    public static final String DATE_FROM_TIMEPICKER = "date_timePicker";
    public static final int    COUNT_ELEMENT_VIEW = 6;
    public static       int    TIME_TO_SLEEP = 60000; //n minutes
}