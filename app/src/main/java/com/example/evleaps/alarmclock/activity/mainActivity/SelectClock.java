package com.example.evleaps.alarmclock.activity.mainActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evleaps.alarmclock.activity.settings.Hashish;
import com.example.evleaps.alarmclock.controller.AlarmReceiver;
import com.example.evleaps.alarmclock.controller.Constant;
import com.example.evleaps.alarmclock.R;
import com.example.evleaps.alarmclock.controller.LoadUnloadObj;
import com.example.evleaps.alarmclock.model.Alarm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.evleaps.alarmclock.controller.Constant.setAlarm;


/** Это главное активити, тут по идее кнопки с будильниками, можно включить и
 * выключить будильник, есть настройки и возможность создать или удалить существующий будильник
 * {@Author Aymaletdinov R}*/

public class SelectClock extends AppCompatActivity implements View.OnClickListener {
    ImageButton       offOn1;   TextView time1;   TextView other1;  Button btn1;
    ImageButton       offOn2;   TextView time2;   TextView other2;  Button btn2;
    ImageButton       offOn3;   TextView time3;   TextView other3;  Button btn3;
    ImageButton       offOn4;   TextView time4;   TextView other4;  Button btn4;
    ImageButton       offOn5;   TextView time5;   TextView other5;  Button btn5;
    ImageButton       offOn6;   TextView time6;   TextView other6;  Button btn6;

    ImageButton       plusAlarmClock;
    Animation         animation = null;
    AlarmManager      alarmManager;
    Set<Alarm>        buttons = new LinkedHashSet<>();

    /**Настроки в статус баре, ПЛЮШКИ включают в себя не важную для будильника функциональность
     * а Settings напрямую регулирует работу будильника*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_clock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hashish:
                startActivity(new Intent(this, Hashish.class));
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Нету", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(this, null));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.btn1:
                menu.add(0,1,0,"Удалить");
                break;
            case R.id.btn2:
                menu.add(0,2,0,"Удалить");
                break;
            case R.id.btn3:
                menu.add(0,3,0,"Удалить");
                break;
            case R.id.btn4:
                menu.add(0,4,0,"Удалить");
                break;
            case R.id.btn5:
                menu.add(0,5,0,"Удалить");
                break;
            case R.id.btn6:
                menu.add(0,6,0,"Удалить");
                break;
            default:
                break;

        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                removeAlarmClock(btn1, other1, time1, offOn1);
                break;
            case 2:
                removeAlarmClock(btn2, other2, time2, offOn2);
                break;
            case 3:
                removeAlarmClock(btn3, other3, time3, offOn3);
                break;
            case 4:
                removeAlarmClock(btn4, other4, time4, offOn4);
                break;
            case 5:
                removeAlarmClock(btn5, other5, time5, offOn5);
                break;
            case 6:
                removeAlarmClock(btn6, other6, time6, offOn6);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plusAlarmClock = (ImageButton) findViewById(R.id.plusAlarmClock);
        plusAlarmClock.setOnClickListener(this);
        alarmManager   = (AlarmManager) getSystemService(ALARM_SERVICE);

        ArrayList<ImageButton> offOn = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);
        ArrayList<TextView>    time  = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);
        ArrayList<TextView>    other = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);
        ArrayList<Button>      btn   = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);

        offOn1 = (ImageButton) findViewById(R.id.offOnn1); offOn.add(offOn1);
        offOn2 = (ImageButton) findViewById(R.id.offOnn2); offOn.add(offOn2);
        offOn3 = (ImageButton) findViewById(R.id.offOnn3); offOn.add(offOn3);
        offOn4 = (ImageButton) findViewById(R.id.offOnn4); offOn.add(offOn4);
        offOn5 = (ImageButton) findViewById(R.id.offOnn5); offOn.add(offOn5);
        offOn6 = (ImageButton) findViewById(R.id.offOnn6); offOn.add(offOn6);

        time1 = (TextView) findViewById(R.id.time1); time.add(time1);
        time2 = (TextView) findViewById(R.id.time2); time.add(time2);
        time3 = (TextView) findViewById(R.id.time3); time.add(time3);
        time4 = (TextView) findViewById(R.id.time4); time.add(time4);
        time5 = (TextView) findViewById(R.id.time5); time.add(time5);
        time6 = (TextView) findViewById(R.id.time6); time.add(time6);

        other1 = (TextView) findViewById(R.id.other1); other.add(other1);
        other2 = (TextView) findViewById(R.id.other2); other.add(other2);
        other3 = (TextView) findViewById(R.id.other3); other.add(other3);
        other4 = (TextView) findViewById(R.id.other4); other.add(other4);
        other5 = (TextView) findViewById(R.id.other5); other.add(other5);
        other6 = (TextView) findViewById(R.id.other6); other.add(other6);

        btn1 = (Button) findViewById(R.id.btn1); btn.add(btn1);
        btn2 = (Button) findViewById(R.id.btn2); btn.add(btn2);
        btn3 = (Button) findViewById(R.id.btn3); btn.add(btn3);
        btn4 = (Button) findViewById(R.id.btn4); btn.add(btn4);
        btn5 = (Button) findViewById(R.id.btn5); btn.add(btn5);
        btn6 = (Button) findViewById(R.id.btn6); btn.add(btn6);

        btn1.setOnClickListener(this);    offOn1.setOnClickListener(this);
        btn2.setOnClickListener(this);    offOn2.setOnClickListener(this);
        btn3.setOnClickListener(this);    offOn3.setOnClickListener(this);
        btn4.setOnClickListener(this);    offOn4.setOnClickListener(this);
        btn5.setOnClickListener(this);    offOn5.setOnClickListener(this);
        btn6.setOnClickListener(this);    offOn6.setOnClickListener(this);

        //контекстное меню
        registerForContextMenu(btn1);
        registerForContextMenu(btn2);
        registerForContextMenu(btn3);
        registerForContextMenu(btn4);
        registerForContextMenu(btn5);
        registerForContextMenu(btn6);

        //перед открытием заполним активити кнопками
        buttons = new LoadUnloadObj(this).unload();

        addAlarmClock(null, null, null, plusAlarmClock, null);//это кнопка + (добавление буд)
        Iterator iterator = buttons.iterator();
        for (int i = 0; i < buttons.size(); i++) {
            Alarm alarm = (Alarm) iterator.next();
            addAlarmClock(btn.get(i), other.get(i), time.get(i), offOn.get(i), alarm.getTime());
            //если у нас уже 6 будильников, кнопка + пропадает
            if (buttons.size()>=6) removeAlarmClock(null, null, null, plusAlarmClock);
        }
    }

    //метод определяет какая кнопка была нажата
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plusAlarmClock:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim );
                plusAlarmClock.startAnimation(animation);
                createAlarmClock();
                break;
            case R.id.offOnn1:
                break;
            case R.id.btn1:

                break;
            default:
                break;
        }
    }

    //тут мы идем на экран таймпикера, возвращаемся и добавляем новый виджкт кнопку будильника
    private void createAlarmClock() {
        Intent intent = new Intent(this, CreateAlarmClock.class);
        finish();
        startActivity(intent);
    }

    private void addAlarmClock(Button btn, TextView other, TextView time,
                               ImageButton offOn, String dateFromTimePicker) {
        try {
            btn.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            offOn.setVisibility(View.VISIBLE);
            other.setVisibility(View.VISIBLE);

            time.setText(dateFromTimePicker);
        } catch (NullPointerException e) {
            offOn.setVisibility(View.VISIBLE);
        }
    }

    private void removeAlarmClock(Button btn, TextView other, TextView time, ImageButton offOn) {
        try {
            btn.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
            offOn.setVisibility(View.INVISIBLE);
            other.setVisibility(View.INVISIBLE);
        }catch (NullPointerException e) {
            offOn.setVisibility(View.INVISIBLE);
        }
        removeAlarmManager(time.getText().toString());
    }

    private void onOffAlarmClock(Boolean offOrOn) {
        if (offOrOn == true) {

        } else {
            Intent intent = new Intent();
            //alarmManager.cancel();
        }
    }

    private void removeAlarmManager(String time) {
        //Удаляет саму сигнализацию
        LoadUnloadObj save = new LoadUnloadObj(this);
        int id = 0;
        for (Alarm a : buttons) {
            setAlarm.clear();

            if (a.getTime() == time) {
               id = a.getID();
                break;
            } else {
                save.load(a);
            }
        }
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pIntent);


    }
}