package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.SpeakerListAdapter;
import com.example.speakerbalancer.speakers.Speaker;

import java.util.Arrays;
import java.util.List;

public class EditSpeakerLayout extends EditConfiguration {
    SeekBar seekBarX, seekBarY;
    TextView selected, position;
    RecyclerView speakerList;
    Button previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seekBarX = findViewById(R.id.seekBarX);
        seekBarY = findViewById(R.id.seekBarY);
        selected = findViewById(R.id.selected);
        position = findViewById(R.id.position);
        speakerList = findViewById(R.id.speakerList);

        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int num = config.getRoomWidth() * progress;
                double text = (double)num / 100;
                position.setText(String.valueOf(text));
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
                position.setText(String.valueOf(text));
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
        speakerList.setAdapter(new SpeakerListAdapter(getApplicationContext(), list, (name, id, button) -> {
            this.selected.setText(getString(R.string.movingSpeaker, name, id));
            if (previousButton == null) previousButton = button;
            previousButton.setEnabled(true);
            button.setEnabled(false);
            previousButton = button;
        }, (name, id) -> {
            this.selected.setText(getString(R.string.editingSpeaker, name, id));
            previousButton.setEnabled(true);
        }));
        speakerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        speakerList.setNestedScrollingEnabled(false);
    }
}