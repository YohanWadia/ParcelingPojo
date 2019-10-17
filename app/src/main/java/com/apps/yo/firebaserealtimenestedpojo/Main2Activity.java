package com.apps.yo.firebaserealtimenestedpojo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        Student s2 = i.getParcelableExtra("myObject");


        Log.e("Activity-2", s2.getName() +"|"+ s2.getTelephone().getPersonal() + "|"+ s2.getAddress().getHome());
    }
}
