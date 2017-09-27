package com.example.evleaps.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.CheckBox;

/**
 * Created by evleaps on 14.09.17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    MediaPlayer  mediaPlayer;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG_LOG", "COOL");
        mediaPlayer = MediaPlayer.create(context, R.raw.boom_boom);
        mediaPlayer.start();

        new CloseAlarmClock();
    }
}
