package com.example.evleaps.alarmclock.activity.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.evleaps.alarmclock.R;

public class Hashish extends AppCompatActivity implements View.OnClickListener {

    ImageButton ok_call;
    ImageButton ok_openInternet;
    ImageButton ok_registration;
    ImageButton ok_personal_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ok_call = (ImageButton) findViewById(R.id.ok_desc_call);
        ok_openInternet = (ImageButton) findViewById(R.id.ok_desc_open_internet);
        ok_registration = (ImageButton) findViewById(R.id.ok_desc_registration);
        ok_personal_account = (ImageButton) findViewById(R.id.ok_desc_personal_account);

        ok_call.setOnClickListener(this);
        ok_openInternet.setOnClickListener(this);
        ok_registration.setOnClickListener(this);
        ok_personal_account.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ok_desc_call:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:89056609998"));
                startActivity(intent);
                break;
            case R.id.ok_desc_open_internet:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.ru/search?q=%D0%BA%D0%B0%D0%BA+%D0%B7%D0%B0%D0%B2%D0%B5%D1%81%D1%82%D0%B8+%D0%B1%D1%83%D0%B4%D0%B8%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA+%D0%BD%D0%B0+%D0%B0ndroid&oq=%D0%BA%D0%B0%D0%BA&aqs=chrome.0.69i59j69i60j69i59j69i57j69i61l2.3231j0j7&sourceid=chrome&ie=UTF-8"));
                startActivity(intent);
                break;
            case R.id.ok_desc_registration:
                intent = new Intent(this, Registration.class);
                intent.putExtra("action", "create");
                startActivity(intent);
                break;
            case R.id.ok_desc_personal_account:
                startActivity(new Intent(this, PersonalAccount.class));
                break;
        }
    }
}
