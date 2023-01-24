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

        // Bad code, just a proof of concept
        // TO-DO:
        // X Set up abstract class for Speaker data type
        // X Create specific speaker classes that extend Speaker abstract class
        // X Improve system type logic
        //  X Make it easier to add new system types
        //  X Update layouts
        //  X Update all necessary variables
        //  X Creating new layout creates array of all speakers
        //  X Updating speaker types re-initializes the array
        // X Have different representations for each type of speaker
        // X Make speakers movable
        //  X Fix bug where they reset position when selected again
        //  X Fix bug where position display doesn't always update correctly
        //  X Add button to reset position(s)
        // X Finalize as many StoredConfig attributes as possible
        // X Save speaker positions to database
        // X Fix LFE showing in speaker list even when removed
        // X Display list of speakers on EditSpeakerLayout activity in a table
        // X Above list has a button for each entry, selecting it allows speaker to be moved
        // X Above list also allows editing each speaker's individual traits
        // X Can click speaker to select (use OnClick event?)
        // X Selected speaker slightly changes color
        // X Reset all speaker positions confirmation
        // X Resetting speaker position should reset X/Y slider
        // - Fix HUD not updating with edited speaker info
        // - Fix boxes not being drawn correctly when resuming EditSpeakerLayout
        // - Convert speaker/system types to enums
        // - Prevent back buttons if unsaved changes exist
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
        set.centerHorizontally(box.getId(), speakerBorder.getId(), ConstraintSet.LEFT, 0, speakerBorder.getId(), ConstraintSet.RIGHT, 0, config.getSystemType().getSpeakers()[index].getXBias());
        set.centerVertically(box.getId(), speakerBorder.getId(), ConstraintSet.TOP, 0, speakerBorder.getId(), ConstraintSet.BOTTOM, 0, config.getSystemType().getSpeakers()[index].getYBias());
        set.centerHorizontally(text.getId(), box.getId(), ConstraintSet.LEFT, 0, box.getId(), ConstraintSet.RIGHT, 0, 0.5F);
        set.centerVertically(text.getId(), box.getId(), ConstraintSet.TOP, 0, box.getId(), ConstraintSet.BOTTOM, 0, 0.5F);
        set.applyTo(speakerBorder);

        speakerBorder.bringToFront();
        findViewById(201 + index).bringToFront();
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