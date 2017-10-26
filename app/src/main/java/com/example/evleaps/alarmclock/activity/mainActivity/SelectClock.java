package com.example.evleaps.alarmclock.activity.mainActivity;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.example.evleaps.alarmclock.activity.settings.Hashish;
import com.example.evleaps.alarmclock.controller.BoxAdapter;
import com.example.evleaps.alarmclock.R;
import com.example.evleaps.alarmclock.controller.LoadUnloadObj;
import com.example.evleaps.alarmclock.model.Alarm;
import java.util.ArrayList;


/**
 * Это главное активити, тут по идее кнопки с будильниками, можно включить и
 * выключить будильник, есть настройки и возможность создать или удалить существующий будильник
 * {@Author Aymaletdinov R}
 */

public class SelectClock extends AppCompatActivity implements View.OnClickListener {

    ImageButton plusAlarmClock;
    AlarmManager alarmManager;

    BoxAdapter boxAdapter;
    ArrayList<Alarm> alarms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plusAlarmClock = (ImageButton) findViewById(R.id.plusAlarmClock);
        plusAlarmClock.setOnClickListener(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //Восстановим данные из памяти
        alarms = new LoadUnloadObj(this).unload();
        Log.d("LOG_TAG", "Alarms size = " + alarms.size());
        boxAdapter = new BoxAdapter(this, alarms, alarmManager);
        // настраиваем список
        ListView listView;
        listView = (ListView) findViewById(R.id.list_view_select_clock);
        listView.setAdapter(boxAdapter);
    }

    /**
     * Настроки в статус баре, ПЛЮШКИ включают в себя не важную для будильника функциональность
     * а Settings напрямую регулирует работу будильника
     */
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

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }



    //метод определяет какая кнопка была нажата
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plusAlarmClock:
                createAlarmClock();
                break;
        }
    }

    //тут мы идем на экран таймпикера, возвращаемся и добавляем новый виджкт кнопку будильника
    private void createAlarmClock() {
        Intent intent = new Intent(this, CreateAlarmClock.class);
        finish();
        startActivity(intent);
    }
}