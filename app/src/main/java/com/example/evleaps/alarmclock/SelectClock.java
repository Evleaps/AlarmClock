package com.example.evleaps.alarmclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class SelectClock extends AppCompatActivity implements View.OnClickListener {

    ImageButton plusAlarmClock;
    LinearLayout selectClockView;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plusAlarmClock = (ImageButton) findViewById(R.id.plusAlarmClock);
        plusAlarmClock.setOnClickListener(this);
        selectClockView = (LinearLayout) findViewById(R.id.viewSelectClock);
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
        String dateFromTimePicker = data.getStringExtra("name");

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, dateFromTimePicker);
        //можно засунуть сет
        ed.putStringSet()
        ed.commit();

        //установили размеры кнопки
        LinearLayout.LayoutParams btnParam
                = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, 200);

        //добавили новую кнопку
        Button newClock = new Button(this);
        newClock.setText(dateFromTimePicker);
        newClock.setTextSize(24);
        selectClockView.addView(newClock, btnParam);

    }
}
