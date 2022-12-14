package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.SpeakerListAdapter;
import com.example.speakerbalancer.speakers.Speaker;

import java.util.Arrays;
import java.util.List;

public class EditSpeakerLayout extends EditConfiguration {
    SeekBar seekBarX, seekBarY;
    TextView selected, positionX, positionY;
    RecyclerView speakerList;
    Button previousButton;
    int selectedSpeaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seekBarX = findViewById(R.id.seekBarX);
        seekBarY = findViewById(R.id.seekBarY);
        selected = findViewById(R.id.selected);
        positionX = findViewById(R.id.positionX);
        positionY = findViewById(R.id.positionY);
        speakerList = findViewById(R.id.speakerList);

        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int num = config.getRoomWidth() * progress;
                double text = (double)num / 100;
                positionX.setText(String.valueOf(text));

                float bias = (float) progress / 100;
                ConstraintSet set = new ConstraintSet();
                set.clone(speakerBorder);
                set.centerHorizontally(selectedSpeaker, speakerBorder.getId(), ConstraintSet.LEFT, 0, speakerBorder.getId(), ConstraintSet.RIGHT, 0, bias);
                set.applyTo(speakerBorder);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int num = config.getRoomLength() * progress;
                double text = (double)num / 100;
                positionY.setText(String.valueOf(text));

                float bias = (float) progress / 100;
                ConstraintSet set = new ConstraintSet();
                set.clone(speakerBorder);
                set.centerVertically(selectedSpeaker, speakerBorder.getId(), ConstraintSet.TOP, 0, speakerBorder.getId(), ConstraintSet.BOTTOM, 0, bias);
                set.applyTo(speakerBorder);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        createSpeakerList();
    }

    protected void setLayout() {
        setContentView(R.layout.activity_edit_speaker_layout);
    }

    private void createSpeakerList() {
        List<Speaker> list = Arrays.asList(config.getSystemType().getSpeakers());
        speakerList.setAdapter(new SpeakerListAdapter(getApplicationContext(), list, (name, id, button, position, xBias, yBias) -> {
            this.selected.setText(getString(R.string.movingSpeaker, name, id));
            if (previousButton == null) previousButton = button;
            previousButton.setEnabled(true);
            button.setEnabled(false);
            previousButton = button;
            selectedSpeaker = position + 101;
            seekBarX.setProgress((int) (xBias * 100));
            seekBarY.setProgress((int) (yBias * 100));
        }, (name, id) -> {
            this.selected.setText(getString(R.string.editingSpeaker, name, id));
            previousButton.setEnabled(true);
        }));
        speakerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        speakerList.setNestedScrollingEnabled(false);
    }
}