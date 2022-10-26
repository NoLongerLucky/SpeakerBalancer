package com.example.speakerbalancer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class EditConfiguration extends AppCompatActivity {
    int id, borderHeight, borderWidth, borderOriginal;
    StoredConfig config;
    TextView name, systemType, roomLength, roomWidth, wallType;
    ConstraintLayout border;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_configuration);
        id = getIntent().getExtras().getInt("id");
        config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);

        name = findViewById(R.id.name);
        systemType = findViewById(R.id.systemType);
        roomLength = findViewById(R.id.roomLength);
        roomWidth = findViewById(R.id.roomWidth);
        wallType = findViewById(R.id.wallType);

        border = findViewById(R.id.border);
        border.post(() -> {
            borderHeight = borderWidth = borderOriginal = border.getMeasuredHeight();
            setRoom();
        });

        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);
        setData();
        setRoom();
    }

    private void setData() {
        if (name != null) name.setText(config.getName());
        if (systemType != null) systemType.setText(config.getSystemType());
        if (roomLength != null) roomLength.setText(String.valueOf(config.getRoomLength()));
        if (roomWidth != null) roomWidth.setText(String.valueOf(config.getRoomWidth()));
        if (wallType != null) wallType.setText(config.getWallType());
    }

    private void setRoom() {
        double length, width, denominator;
        borderHeight = borderWidth = borderOriginal;
        length = config.getRoomLength();
        width = config.getRoomWidth();
        if (length > width) {
            denominator = length / width;
            borderWidth /= denominator;
        } else if (width > length) {
            denominator = width / length;
            borderHeight /= denominator;
        }
        border.getLayoutParams().height = borderHeight;
        border.getLayoutParams().width = borderWidth;
        border.setLayoutParams(border.getLayoutParams());
    }

    public void launch_editConfigInfo(View v) {
        Intent i = new Intent(this, EditConfigurationInfo.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}