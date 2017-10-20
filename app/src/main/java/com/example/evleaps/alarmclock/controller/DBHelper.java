package com.example.evleaps.alarmclock.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.evleaps.alarmclock.controller.Constant.DATABASE_NAME;
import static com.example.evleaps.alarmclock.controller.Constant.DATABASE_TABLE_CONTACTS;
import static com.example.evleaps.alarmclock.controller.Constant.DATABASE_VERSION;

/**
 * Created by RAymaletdin on 10/19/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("LOG_TAG", "--- onCreate database ---");

        // создаем таблицу с полями
        db.execSQL("create table " + DATABASE_NAME + " ("
                + "id integer primary key autoincrement,"
                + "lastName text,"
                + "firstName text,"
                + "login text,"
                + "password text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}