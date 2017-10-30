package com.example.evleaps.alarmclock.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.evleaps.alarmclock.model.Alarm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
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

    public void load(ArrayList<Alarm> alarms) {
        //сохраним текущее состояние листа в gson
        String gsonAlarm = new Gson().toJson(alarms);

        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVE_DATE_CLOCK, gsonAlarm);
        ed.clear();
        ed.commit();
        Log.d("LOG_TAG", "");

    }

    public ArrayList<Alarm> unload() {
//Загружаю из памяти gson лист и вревращаю в распарсенный лист.
        String s = sPref.getString(SAVE_DATE_CLOCK, "");
        ArrayList<Alarm> alarms = new Gson().fromJson(s, new TypeToken<ArrayList<Alarm>>(){}.getType());
        if (alarms != null)
            return alarms;
        else
            return new ArrayList<Alarm>();
    }

    public void refresh(Alarm alarm) {
        ArrayList<Alarm> alarms = unload();
        for (Alarm a : alarms) {
            if (a.getID() == alarm.getID())
                a.setState(alarm.isState());
            break;
        }
        load(alarms);
        Log.d("LOG_TAG", "Обновлено состояние будильника. вкл/выкл");

    }
}
