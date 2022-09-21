package com.example.speakerbalancer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class EditConfiguration extends AppCompatActivity {
    int id;
    TextView name, systemType, roomLength, roomWidth, wallType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_configuration);
        id = getIntent().getExtras().getInt("id");
        setData();

        name = findViewById(R.id.name);
        systemType = findViewById(R.id.systemType);
        roomLength = findViewById(R.id.roomLength);
        roomWidth = findViewById(R.id.roomWidth);
        wallType = findViewById(R.id.wallType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {
        StoredConfig config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);
        if (name != null) name.setText(config.getName());
        if (systemType != null) systemType.setText(config.getSystemType());
        if (roomLength != null) roomLength.setText(String.valueOf(config.getRoomLength()));
        if (roomWidth != null) roomWidth.setText(String.valueOf(config.getRoomWidth()));
        if (wallType != null) wallType.setText(config.getWallType());
    }

    public void launch_editConfigInfo(View v) {
        Intent i = new Intent(this, EditConfigurationInfo.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}