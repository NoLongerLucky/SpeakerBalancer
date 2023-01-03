package com.example.speakerbalancer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class EditConfiguration extends AppCompatActivity {
    int id, borderHeight, borderWidth, borderOriginal;
    StoredConfig config;
    TextView name, systemType, roomLength, roomWidth, wallType, boxLengthDisplay, boxWidthDisplay;
    ConstraintLayout border;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
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

    protected void setLayout() {
        setContentView(R.layout.activity_edit_configuration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);
        setData();
        setRoom();
    }

    protected void setData() {
        if (name != null) name.setText(config.getName());
        if (systemType != null) systemType.setText(config.getSystemType());
        if (roomLength != null) roomLength.setText(String.valueOf(config.getRoomLength()));
        if (roomWidth != null) roomWidth.setText(String.valueOf(config.getRoomWidth()));
        if (wallType != null) wallType.setText(config.getWallType());
    }

    protected void setRoom() {
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

        boxLengthDisplay = findViewById(R.id.boxLengthDisplay);
        boxWidthDisplay = findViewById(R.id.boxWidthDisplay);
        String lengthDisplay = " " + config.getRoomLength() + " ft ";
        String widthDisplay = " " + config.getRoomWidth() + " ft ";
        boxLengthDisplay.setText(lengthDisplay);
        boxWidthDisplay.setText(widthDisplay);

        // Bad code, just a proof of concept
        // TO-DO:
        // - Set up enums(?) for system types and amounts of speakers
        // - Have different representations for each type of speaker
        // - Save speaker positions to database
        // - Display list of speakers on EditSpeakerLayout activity
        // - Above list is selectable, allowing positions to be changed with sliders
        int repeats;
        switch (config.getSystemType()) {
            case "3.1":
                repeats = 1;
                break;
            case "5.1":
                repeats = 2;
                break;
            case "7.1":
                repeats = 3;
                break;
            default:
                repeats = 4;
                break;
        }
        for (int i = 0; i < repeats; i++) createSpeakerBox(i);
    }

    protected void createSpeakerBox(int index) {
        ConstraintLayout mConstraintLayout = findViewById(R.id.border);
        ConstraintSet set = new ConstraintSet();
        View view = new View(this);
        view.setId(View.generateViewId());
        view.setBackgroundColor(Color.parseColor("#000000"));
        view.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
        mConstraintLayout.addView(view,index);
        set.clone(mConstraintLayout);
        set.connect(view.getId(), ConstraintSet.TOP, mConstraintLayout.getId(), ConstraintSet.TOP, 100 * index);
        set.connect(view.getId(), ConstraintSet.LEFT, mConstraintLayout.getId(), ConstraintSet.LEFT, 30);
        set.applyTo(mConstraintLayout);
    }

    public void launch_editConfigInfo(View v) {
        Intent i = new Intent(this, EditConfigurationInfo.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    public void launch_editSpeakerLayout(View v) {
        Intent i = new Intent(this, EditSpeakerLayout.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}