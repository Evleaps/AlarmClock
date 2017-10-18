package com.example.evleaps.alarmclock.controller;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.evleaps.alarmclock.model.Alarm;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static com.example.evleaps.alarmclock.controller.Constant.FIND_ID;
import static com.example.evleaps.alarmclock.controller.Constant.SAVE_DATE_CLOCK;
import static com.example.evleaps.alarmclock.controller.Constant.setFindId;

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
//Загружаю из памяти gson, превращаю их в объекты и метод возвращает set с этими объектами
        LinkedHashSet<Alarm> unload = new LinkedHashSet<>();
        Set<String> gsonAlarm = sPref.getStringSet(SAVE_DATE_CLOCK, new LinkedHashSet<String>());
        for (String s : gsonAlarm) {
            Alarm alarm = new Gson().fromJson(s, Alarm.class);
            unload.add(alarm);
        }

        return unload;
    }

    public int searchId() {
        try {
            setFindId = sPref.getStringSet(FIND_ID, new HashSet<String>());
            ArrayList<String> list = new ArrayList<>(setFindId);
            for (int i = 1; i <= 6; i++) {
                if (!list.contains(i+""))
                    return i;
            }
        }catch (NullPointerException e) {}
        return 0;
    }


}
