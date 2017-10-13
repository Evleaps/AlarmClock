package com.example.evleaps.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by evleaps on 14.09.17.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG_LOG", "COOL");
        //запускаем активити с кнопкой отмены
        Intent i = new Intent();
        i.setClassName("com.example.evleaps.alarmclock", "com.example.evleaps.alarmclock.CloseAlarmClock");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
