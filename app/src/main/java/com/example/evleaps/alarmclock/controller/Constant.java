package com.example.evleaps.alarmclock.controller;

import com.example.evleaps.alarmclock.activity.mainActivity.SelectClock;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Constant for {@link SelectClock}
 */

public class Constant {
    public static Set<String>  setAlarm  = new LinkedHashSet<>();
    public static Set<String>  setFindId = new HashSet<>();
    public static final String SAVE_DATE_CLOCK = "save_date_newAlarmClock";
    public static final String FIND_ID = "save_date_newAlarmClock";
    public static final String KEY_LOAD_JBJ_IN_SPREF = "save_obj";
    public static final String DATE_FROM_TIMEPICKER = "date_timePicker";
    public static final int    COUNT_ELEMENT_VIEW = 6;
    public static       int    TIME_TO_SLEEP = 60000; //n minutes

    public static final int    DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";
    public static final String DATABASE_TABLE_CONTACTS = "contact";
}