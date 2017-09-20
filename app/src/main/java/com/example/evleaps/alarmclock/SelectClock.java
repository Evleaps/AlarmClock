package com.example.evleaps.alarmclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.evleaps.alarmclock.Constant.DATE_FROM_TIMEPICKER;
import static com.example.evleaps.alarmclock.Constant.SAVE_DATE_CLOCK;
import static com.example.evleaps.alarmclock.Constant.SIZE_TEXT;

/** Это главное активити, тут по идее кнопки с будильниками, можно включить и
 * выключить будильник, есть настройки и возможность создать или удалить существующий будильник
 * {@Author Aymaletdinov R}*/

public class SelectClock extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sPref;
    ImageButton       plusAlarmClock;
    RelativeLayout    selectClockView;

    int         btnId   = 0;
    Set<String> buttons = new LinkedHashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plusAlarmClock = (ImageButton) findViewById(R.id.plusAlarmClock);
        plusAlarmClock.setOnClickListener(this);
        selectClockView = (RelativeLayout) findViewById(R.id.viewSelectClock);

        //перед открытием заполним активити кнопками
        sPref = getPreferences(MODE_PRIVATE);
        buttons = sPref.getStringSet(SAVE_DATE_CLOCK, new LinkedHashSet<String>());

        for (String s : buttons) {
            addViewButton(s);
        }
        Toast.makeText(SelectClock.this, "Будильников всего: "+buttons.size(), Toast.LENGTH_LONG).show();
    }

    //метод определяет какая кнопка была нажата
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plusAlarmClock:
                createAlarmClock();
                break;
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

    private void addViewButton(String dateFromTimePicker) {
        //установили размеры кнопки
        RelativeLayout.LayoutParams btnParam
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, Constant.SIZE_HEIGHT_BUTTON);
        if (btnId != 0)
            btnParam.addRule(RelativeLayout.BELOW, btnId-1);

        //добавили новую кнопку
        Button newClock = new Button(this);
        newClock.setText(dateFromTimePicker);
        newClock.setTextSize(SIZE_TEXT);
        newClock.setBackgroundColor(Color.GREEN);
        newClock.setId(btnId);

        //установили размеры кнопки on\off
        RelativeLayout.LayoutParams btnParamOnOff
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnParamOnOff.addRule(RelativeLayout.ALIGN_RIGHT, btnId);
        btnParamOnOff.addRule(RelativeLayout.BELOW, btnId-1);
        btnParamOnOff.setMargins(0,75,75,0);//отступы



        Button newClockOnOff = new Button(this);
        newClockOnOff.setBackgroundColor(Color.RED);

        selectClockView.addView(newClock, btnParam);
        selectClockView.addView(newClockOnOff, btnParamOnOff);
        btnId++;
    }
}
