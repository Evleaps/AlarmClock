package com.example.evleaps.alarmclock.activity.settings;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evleaps.alarmclock.R;
import com.example.evleaps.alarmclock.controller.DBHelper;
import com.example.evleaps.alarmclock.model.User;

import static com.example.evleaps.alarmclock.controller.Constant.DATABASE_NAME;

public class PersonalAccount extends AppCompatActivity {

    private TextView firstName;
    private TextView lastName;
    private TextView login;
    private TextView id_user;
    private Button clear;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        firstName = (TextView) findViewById(R.id.first_name);
        id_user = (TextView) findViewById(R.id.id_user);
        lastName = (TextView) findViewById(R.id.last_name);
        login = (TextView) findViewById(R.id.login);
        clear = (Button) findViewById(R.id.personal_account_clear);
        update = (Button) findViewById(R.id.personal_account_update);

        querySQL();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSQL();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSQL();
            }
        });
    }

    private void updateSQL() {
        Intent intent = new Intent(this, Registration.class);
        try {
            intent.putExtra("action", "update:" + id_user.getText().toString().split("ID: ")[1]);
            startActivity(intent);

            Intent thisIntent = new Intent(this, getClass());
            this.finish();
            startActivity(thisIntent);
        } catch (ArrayIndexOutOfBoundsException e) {
            //Если нет у нас id, нет никого, то мы не падаем
            Toast.makeText(this, "Сначала создайте профиль", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearSQL() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DATABASE_NAME, null, null);
        Log.d("LOG_TAG", "Из БД: "+DATABASE_NAME+" удалены записи");
        db.close();
        dbHelper.close();

        Intent intent = new Intent(this, getClass());
        this.finish();
        startActivity(intent);
    }


    private void querySQL() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DATABASE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = cursor.getColumnIndex("id");
            int firstNameColIndex = cursor.getColumnIndex("firstName");
            int lastNameColIndex = cursor.getColumnIndex("lastName");
            int loginColIndex = cursor.getColumnIndex("login");
            int passwordColIndex = cursor.getColumnIndex("password");

            do {
                Log.d("LOG_TAG",
                        "ID = " + cursor.getInt(idColIndex) +
                                ", firstName = " + cursor.getString(firstNameColIndex) +
                                ", lastName = " + cursor.getString(lastNameColIndex) +
                                ", login = " + cursor.getString(loginColIndex));
                id_user.setText("ID: " + cursor.getInt(idColIndex));
                firstName.setText("First name: " + cursor.getString(firstNameColIndex));
                lastName.setText("Last name: " + cursor.getString(lastNameColIndex));
                login.setText("Login: " + cursor.getString(loginColIndex));

                /**Добавить список юзеров и их отображение, что-бы можно было посмотреть профиль
                 * и юзай retrofit*/

                User user = new User();
                user.setId( cursor.getInt(idColIndex));
                user.setFirstName(cursor.getString(firstNameColIndex));
                user.setLastName(cursor.getString(lastNameColIndex));
                user.setLogin(cursor.getString(loginColIndex));
                user.setPassword(cursor.getString(passwordColIndex));


            } while (cursor.moveToNext());
        } else Log.d("LOG_TAG", "0 rows");
        cursor.close();
        db.close();
        dbHelper.close();
    }

}
