package com.example.evleaps.alarmclock.controller;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.evleaps.alarmclock.model.Alarm;
import com.google.gson.Gson;

import java.util.LinkedHashSet;

import static android.content.Context.MODE_PRIVATE;
import static com.example.evleaps.alarmclock.controller.Constant.SAVE_DATE_CLOCK;

/**
 * Класс занимается сохранением объекта {@link com.example.evleaps.alarmclock.model.Alarm} на телефон
 * для этого используется парсинг в json и сохранение в set используя SharedPreferences
 */

public class LoadUnloadObj{
    SharedPreferences sPref;

    public LoadUnloadObj(Context context) {
        sPref = context.getSharedPreferences(Constant.KEY_LOAD_JBJ_IN_SPREF ,MODE_PRIVATE);
    }

    public void load(Alarm alarm) {
        //тк spref очень хочет, что-бы в Set была строка параметром, преобразуем объект в gson
        String gsonAlarm = new Gson().toJson(alarm);
        Constant.setAlarm.add(gsonAlarm);

        SharedPreferences.Editor ed = sPref.edit();
        ed.putStringSet(SAVE_DATE_CLOCK, Constant.setAlarm);
        ed.clear();
        ed.commit();
    }

    public LinkedHashSet<Alarm> unload() {

        LinkedHashSet<Alarm> unload = new LinkedHashSet<>();
        for (String s : Constant.setAlarm) {
            Alarm alarm = sPref.getStringSet(s, Alarm.class);
            unload.add(alarm);
        }

        return unload;
    }
}
