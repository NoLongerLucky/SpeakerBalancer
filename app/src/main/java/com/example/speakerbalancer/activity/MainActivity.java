package com.example.speakerbalancer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.speakerbalancer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launch_newConfig(View v) {
        Intent i = new Intent(this, NewConfiguration.class);
        startActivity(i);
    }

    public void launch_loadConfig(View v) {
        Intent i = new Intent(this, LoadConfiguration.class);
        startActivity(i);
    }
}