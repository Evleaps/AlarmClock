package com.example.evleaps.alarmclock.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.evleaps.alarmclock.R;
import com.example.evleaps.alarmclock.model.Alarm;

/**
 * Этот адаптер для чекбокса будильников, см {@link com.example.evleaps.alarmclock.activity.mainActivity.SelectClock}
 */

public class BoxAdapter extends BaseAdapter {
    private PendingIntent pIntent;
    private Context context;
    private AlarmManager alarmManager;
    private LayoutInflater layoutInflater;
    private ArrayList<Alarm> obj;

    public BoxAdapter(Context context, ArrayList<Alarm> obj, AlarmManager alarmManager) {
        this.context = context;
        this.obj = obj;
        this.alarmManager = alarmManager;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return obj.size();
    }
    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return obj.get(position);
    }
    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }
    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_form_item, parent, false);
        } else {
            Log.d("LOG_TAG", "НЕ пущу");
        }

        Alarm alarm = getAlarm(position);
        // заполняем View в пункте списка данными из товаров: наименование, цена и картинка
        ((TextView)view.findViewById(R.id.other1)).setText("Будильник");
        ((TextView)view.findViewById(R.id.time1)).setText(alarm.getTime());
        CheckBox cb = (CheckBox) view.findViewById(R.id.checkbox1);

        // пишем позицию
        cb.setTag(position);
        // заполняем данными :(on or off)
        cb.setChecked(alarm.isState());
        // присваиваем чекбоксу обработчик
        cb.setOnCheckedChangeListener(myCheckChangeList);
        return view;
    }

    //обработчик для кнопки вкл\выкл будильника
    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // меняем данные (on or off)
            Alarm alarm = getAlarm((Integer) buttonView.getTag());
            alarm.setState(isChecked);
            new LoadUnloadObj(context).refresh(alarm);

         /**   т.к. при прокрутке будет пересоздаваться view нужно
             сейчас произвести логику откл\вкл сигнализации*/
            Intent intent = new Intent(context, AlarmReceiver.class);
            pIntent = PendingIntent.getBroadcast(context, alarm.getID(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

         if (isChecked == true) {
             new SetAlarmManager(alarm);//устанавливает
             alarmManager.set(AlarmManager.RTC_WAKEUP,
                     new SetAlarmManager(alarm).returnCalendar().getTimeInMillis(), pIntent);
             Log.d("LOG_TAG", "Будильник " + alarm.getID() + " установлен");
         } else {
             alarmManager.cancel(pIntent);
             Log.d("LOG_TAG", "Будильник " + alarm.getID() + " отменен");
         }
        }
    };

    // товар по позиции
    Alarm getAlarm(int position) {
        return ((Alarm) getItem(position));
    }
}

