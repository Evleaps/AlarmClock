package com.example.evleaps.alarmclock.activity.settings;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.evleaps.alarmclock.R;
import com.example.evleaps.alarmclock.controller.Constant;
import com.example.evleaps.alarmclock.controller.DBHelper;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    EditText firstName;
    EditText lastName;
    EditText login;
    EditText password;
    Button   submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstName = (EditText) findViewById(R.id.first_name);
        lastName  = (EditText) findViewById(R.id.last_name);
        login     = (EditText) findViewById(R.id.login);
        password  = (EditText) findViewById(R.id.password);
        submit    = (Button) findViewById(R.id.submit);

        dbHelper = new DBHelper(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String firstName = this.firstName.getText().toString();
        String lastName  = this.lastName.getText().toString();
        String login     = this.login.getText().toString();
        String password  = this.password.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // создаем объект для данных
        ContentValues cv = new ContentValues();

        switch (v.getId()) {
            case R.id.submit:
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                Log.d("LOG_TAG", "--- Insert in mytable: ---");
                cv.put("firstName", firstName);
                cv.put("lastName",  lastName);
                cv.put("login", login);
                cv.put("password", password);

                //узнаем, что от нас хотят, update или create
                Intent   intent = getIntent();
                String action = intent.getStringExtra("action");
                if (action.equals("create"))
                    saveDb(db, cv);
                else
                    updateDB(db, cv, action.split("update:")[1]);
        }
        dbHelper.close();
        this.finish();
    }

    private void saveDb(SQLiteDatabase db, ContentValues cv) {
        // вставляем запись
        db.insert(Constant.DATABASE_NAME, null, cv);
    }

    private void updateDB(SQLiteDatabase db, ContentValues cv, String id) {
        if (!id.equalsIgnoreCase("")) {
            long g = db.update(Constant.DATABASE_NAME, cv, "id = ?", new String[]{id});
            Log.d("LOG_TAG", "Обновлены записи в SQL: " + g);
            Log.d("LOG_TAG", "ID = " + id);
        }
    }
}
