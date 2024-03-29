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
    TextView nameText, systemTypeText, roomLengthText, roomWidthText, wallMaterialText, boxLengthDisplay, boxWidthDisplay;
    ConstraintLayout border, speakerBorder;
    View leftWall, rightWall, topWall, bottomWall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        id = getIntent().getExtras().getInt("id");
        config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);

        nameText = findViewById(R.id.nameInput);
        systemTypeText = findViewById(R.id.systemTypeSpinner);
        roomLengthText = findViewById(R.id.roomLengthInput);
        roomWidthText = findViewById(R.id.roomWidthInput);
        wallMaterialText = findViewById(R.id.wallMaterialSpinner);

        border = findViewById(R.id.border);
        border.post(() -> {
            borderHeight = borderWidth = borderOriginal = border.getMeasuredHeight();
            setRoom();
        });
        speakerBorder = findViewById(R.id.speakerBorder);

        leftWall = findViewById(R.id.leftWall);
        rightWall = findViewById(R.id.rightWall);
        topWall = findViewById(R.id.topWall);
        bottomWall = findViewById(R.id.bottomWall);

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
        if (nameText != null) nameText.setText(config.getName());
        if (systemTypeText != null) systemTypeText.setText(config.getSystemType().getName());
        if (roomLengthText != null) roomLengthText.setText(String.valueOf(config.getRoomLength()));
        if (roomWidthText != null) roomWidthText.setText(String.valueOf(config.getRoomWidth()));
        if (wallMaterialText != null) wallMaterialText.setText(config.getWallMaterial().displayName());
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

        leftWall.setBackgroundColor(config.getWallMaterial().getColor());
        rightWall.setBackgroundColor(config.getWallMaterial().getColor());
        topWall.setBackgroundColor(config.getWallMaterial().getColor());
        bottomWall.setBackgroundColor(config.getWallMaterial().getColor());

        speakerBorder.removeAllViewsInLayout();
        for (int i = 0; i < config.getSystemType().getAmount(); i++) createSpeakerBox(i);
    }

    protected void createSpeakerBox(int index) {
        View box = new View(this);
        box.setId(101 + index);
        box.setBackgroundColor(Color.parseColor("#000000"));
        box.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
        box.setOnClickListener(v -> boxClickListener(box.getId()));
        speakerBorder.addView(box, index);

        TextView text = new TextView(this);
        text.setId(201 + index);
        text.setText(config.getSystemType().getSpeakers()[index].getChannel().getId());
        text.setTextColor(Color.parseColor("#FFFFFF"));
        speakerBorder.addView(text, index);

        ConstraintSet set = new ConstraintSet();
        set.clone(speakerBorder);
        set.centerHorizontally(box.getId(), speakerBorder.getId(), ConstraintSet.LEFT, 0, speakerBorder.getId(), ConstraintSet.RIGHT, 0, xBias(index));
        set.centerVertically(box.getId(), speakerBorder.getId(), ConstraintSet.TOP, 0, speakerBorder.getId(), ConstraintSet.BOTTOM, 0, yBias(index));
        set.centerHorizontally(text.getId(), box.getId(), ConstraintSet.LEFT, 0, box.getId(), ConstraintSet.RIGHT, 0, 0.5F);
        set.centerVertically(text.getId(), box.getId(), ConstraintSet.TOP, 0, box.getId(), ConstraintSet.BOTTOM, 0, 0.5F);
        set.applyTo(speakerBorder);

        speakerBorder.bringToFront();
        findViewById(201 + index).bringToFront();
    }

    protected float xBias(int id) {
        return config.getSystemType().getSpeakers()[id].getXBias();
    }

    protected float yBias(int id) {
        return config.getSystemType().getSpeakers()[id].getYBias();
    }

    protected void boxClickListener(int id) { }

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