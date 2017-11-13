package com.example.muhammadaziz.sms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AwalActivity extends AppCompatActivity {

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
    }

    public void pesan(View view) {
        i= new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void gmail(View view) {
        i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_APP_EMAIL);
        startActivity(i);
    }


}
