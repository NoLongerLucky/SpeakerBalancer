package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.SpeakerListAdapter;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.TempConfig;
import com.example.speakerbalancer.speakers.Speaker;
import com.example.speakerbalancer.systems.Channel;

import java.util.Arrays;
import java.util.List;

public class EditSpeakerLayout extends EditConfiguration {
    TempConfig unsavedConfig;
    SeekBar seekBarX, seekBarY;
    TextView selected, positionX, positionY;
    RecyclerView speakerList;
    Button saveLayout, previousButton;
    int selectedSpeaker = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unsavedConfig = new TempConfig(getApplicationContext(), id);

        seekBarX = findViewById(R.id.seekBarX);
        seekBarY = findViewById(R.id.seekBarY);
        selected = findViewById(R.id.selected);
        positionX = findViewById(R.id.positionX);
        positionY = findViewById(R.id.positionY);
        speakerList = findViewById(R.id.speakerList);
        saveLayout = findViewById(R.id.saveLayout);
        saveLayout.setOnClickListener(view -> saveLayout());

        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int num = config.getRoomWidth() * progress;
                double text = (double)num / 100;
                positionX.setText(String.valueOf(text));

                if (!fromUser) return;
                float bias = (float) progress / 100;
                changeSpeakerXBias(bias);
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

                if (!fromUser) return;
                float bias = (float) progress / 100;
                changeSpeakerYBias(bias);
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
        speakerList.setAdapter(new SpeakerListAdapter(getApplicationContext(), list, (speaker, button, position) -> {
            this.selected.setText(getString(R.string.movingSpeaker, speaker.getName(), speaker.getChannel().getId()));
            (previousButton == null ? button : previousButton).setEnabled(true);
            button.setEnabled(false);
            previousButton = button;
            selectedSpeaker = position;
            seekBarX.setProgress((int) (unsavedConfig.xBiases[position] * 100));
            seekBarY.setProgress((int) (unsavedConfig.yBiases[position] * 100));
        }, (channel, position) -> {
            selectedSpeaker = position;
            changeSpeakerXBias(Channel.values()[channel.getIndex()].getxBias());
            changeSpeakerYBias(Channel.values()[channel.getIndex()].getyBias());
        }, (speaker) -> {
            this.selected.setText(getString(R.string.editingSpeaker, speaker.getName(), speaker.getChannel().getId()));
            previousButton.setEnabled(true);
        }));
        speakerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        speakerList.setNestedScrollingEnabled(false);
    }

    private void changeSpeakerXBias(float bias) {
        if (selectedSpeaker < 0) return;
        ConstraintSet set = new ConstraintSet();
        set.clone(speakerBorder);
        set.centerHorizontally(selectedSpeaker + 101, speakerBorder.getId(), ConstraintSet.LEFT, 0, speakerBorder.getId(), ConstraintSet.RIGHT, 0, bias);
        set.applyTo(speakerBorder);
        unsavedConfig.xBiases[selectedSpeaker] = bias;
    }

    private void changeSpeakerYBias(float bias) {
        if (selectedSpeaker < 0) return;
        ConstraintSet set = new ConstraintSet();
        set.clone(speakerBorder);
        set.centerVertically(selectedSpeaker + 101, speakerBorder.getId(), ConstraintSet.TOP, 0, speakerBorder.getId(), ConstraintSet.BOTTOM, 0, bias);
        set.applyTo(speakerBorder);
        unsavedConfig.yBiases[selectedSpeaker] = bias;
    }

    private void saveLayout() {
        // DOES NOT WORK
        config.getSystemType().setxBiases(unsavedConfig.xBiases);
        config.getSystemType().setyBiases(unsavedConfig.yBiases);
        AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(config);
        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();
        finish();
    }
}