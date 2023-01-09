package com.example.speakerbalancer.activity;

import android.os.Bundle;
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
    TextView position;
    RecyclerView speakerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seekBarX = findViewById(R.id.seekBarX);
        seekBarY = findViewById(R.id.seekBarY);
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
        List<Speaker> list = Arrays.asList(config.getSystemType().speakers);
        speakerList.setAdapter(new SpeakerListAdapter(getApplicationContext(), list, (position, id) -> {
            this.position.setText("Move Button Clicked");
        }, (position, id) -> {
            this.position.setText("Edit Button Clicked");
        }));
        speakerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}