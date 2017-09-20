package com.example.evleaps.alarmclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.evleaps.alarmclock.Constant.DATE_FROM_TIMEPICKER;
import static com.example.evleaps.alarmclock.Constant.SAVE_DATE_CLOCK;


/** Это главное активити, тут по идее кнопки с будильниками, можно включить и
 * выключить будильник, есть настройки и возможность создать или удалить существующий будильник
 * {@Author Aymaletdinov R}*/

public class SelectClock extends AppCompatActivity implements View.OnClickListener {


    ImageButton       offOn1;    TextView time1;   TextView other1;  Button btn1;
    ImageButton       offOn2;    TextView time2;   TextView other2;  Button btn2;
    ImageButton       offOn3;    TextView time3;   TextView other3;  Button btn3;
    ImageButton       offOn4;    TextView time4;   TextView other4;  Button btn4;
    ImageButton       offOn5;    TextView time5;   TextView other5;  Button btn5;
    ImageButton       offOn6;    TextView time6;   TextView other6;  Button btn6;

    ImageButton       plusAlarmClock;
    SharedPreferences sPref;
    Set<String>       buttons = new LinkedHashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plusAlarmClock = (ImageButton) findViewById(R.id.plusAlarmClock);
        plusAlarmClock.setOnClickListener(this);

        ArrayList<ImageButton> offOn = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);
        ArrayList<TextView> time  = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);
        ArrayList<TextView> other = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);
        ArrayList<Button> btn   = new ArrayList<>(Constant.COUNT_ELEMENT_VIEW);

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


        //перед открытием заполним активити кнопками
        sPref = getPreferences(MODE_PRIVATE);
        buttons = sPref.getStringSet(SAVE_DATE_CLOCK, new LinkedHashSet<String>());

        addAlarmClock(null, null, null, plusAlarmClock, null);
        Iterator iterator = buttons.iterator();
        for (int i = 0; i < buttons.size(); i++) {
            addAlarmClock(btn.get(i), other.get(i), time.get(i), offOn.get(i), iterator.next().toString());
            if (buttons.size()>=6) removeAlarmClock(null, null, null, plusAlarmClock);
        }
    }

    //метод определяет какая кнопка была нажата
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plusAlarmClock:
                createAlarmClock();
                break;
            case R.id.offOnn1:

            default:
                break;
        }
    }

    //тут мы идем на экран таймпикера, возвращаемся и добавляем новый виджкт кнопку будильника
    private void createAlarmClock() {
        Intent intent = new Intent(this, AlarmClock.class);
        startActivityForResult(intent, 1);
    }
    //метод возвращает данные полученные на виджете с таймпикером
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        String dateFromTimePicker = data.getStringExtra(DATE_FROM_TIMEPICKER);
        buttons.add(dateFromTimePicker);

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putStringSet(SAVE_DATE_CLOCK, buttons);
        ed.commit();
        //перезагружаю активити
        Intent i = new Intent( this , this.getClass() );
        finish();
        this.startActivity(i);
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
    }

    private void onAlarmClock() {

    }

}
