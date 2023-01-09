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
    ConstraintLayout border;

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
        wallMaterialText = findViewById(R.id.wallTypeSpinner);

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

        // Bad code, just a proof of concept
        // TO-DO:
        // X Set up abstract class for Speaker data type
        // X Create specific speaker classes that extend Speaker abstract class
        // X Improve system type logic
        //  X Make it easier to add new system types
        //  X Update layouts
        //  X Update all necessary variables
        //  X Creating new layout creates array of all speakers
        //  ~ Updating speaker types re-initializes the array
        // X Have different representations for each type of speaker
        // - Make speakers movable
        // - Save speaker positions to database
        // - Display list of speakers on EditSpeakerLayout activity in a table
        // - Above list has a button for each entry, selecting it allows speaker to be moved
        // - Above list also allows editing each speaker's individual traits
        for (int i = 0; i < config.getSystemType().getAmount(); i++) createSpeakerBox(i);
        if (!config.getSystemType().lfe.isChecked()) {
            border.removeView(findViewById(101 + config.getSystemType().amount));
            border.removeView(findViewById(201 + config.getSystemType().amount));
        }
    }

    protected void createSpeakerBox(int index) {
        View box = new View(this);
        box.setId(101 + index);
        box.setBackgroundColor(Color.parseColor("#000000"));
        box.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
        border.addView(box, index);

        TextView text = new TextView(this);
        text.setId(201 + index);
        text.setText(config.getSystemType().speakers[index].channel.id);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        border.addView(text, index);

        ConstraintSet set = new ConstraintSet();
        set.clone(border);
        set.connect(box.getId(), ConstraintSet.TOP, border.getId(), ConstraintSet.TOP, 100 * index);
        set.connect(box.getId(), ConstraintSet.LEFT, border.getId(), ConstraintSet.LEFT, 30);
        set.centerHorizontally(text.getId(), box.getId(), ConstraintSet.LEFT, 0, box.getId(), ConstraintSet.RIGHT, 0, 0.5F);
        set.centerVertically(text.getId(), box.getId(), ConstraintSet.TOP, 0, box.getId(), ConstraintSet.BOTTOM, 0, 0.5F);
        set.applyTo(border);

        findViewById(101 + index).bringToFront();
        findViewById(201 + index).bringToFront();
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