package com.example.speakerbalancer.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EditSpeakerLayout extends EditConfiguration {
    public static TempConfig unsavedConfig;
    SeekBar seekBarX, seekBarY;
    TextView selected, positionX, positionY;
    RecyclerView speakerList;
    Button saveLayout, resetAllSpeakerPositions, previousButton;
    int speaker = -1;

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
        resetAllSpeakerPositions = findViewById(R.id.resetAllSpeakerPositions);
        resetAllSpeakerPositions.setOnClickListener(view -> resetAllSpeakerPositions());

        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int num = config.getRoomWidth() * progress;
                double text = (double)num / 100;
                positionX.setText(String.valueOf(text));

                if (!fromUser || speaker < 0) return;
                changeSpeakerXBias(speaker, (float) progress / 100);
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

                if (!fromUser || speaker < 0) return;
                changeSpeakerYBias(speaker, (float) progress / 100);
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

    @Override
    protected void onResume() {
        super.onResume();
        createSpeakerList();
        highlight(speaker);
    }

    protected void boxClickListener(int id) {
        id -= 101;
        Speaker speaker = config.getSystemType().getSpeakers()[id];
        Button button = Objects.requireNonNull(speakerList.findViewHolderForAdapterPosition(id)).itemView.findViewById(R.id.move);
        int position = id;
        selectSpeaker(speaker, button, position);
    }

    private void selectSpeaker(Speaker speaker, Button button, int position) {
        selected.setText(getString(R.string.movingSpeaker, speaker.getName(), speaker.getChannel().getId()));
        highlight(position);
        (previousButton == null ? button : previousButton).setEnabled(true);
        button.setEnabled(false);
        previousButton = button;
        this.speaker = position;
        seekBarX.setProgress((int) (unsavedConfig.xBiases[position] * 100));
        seekBarY.setProgress((int) (unsavedConfig.yBiases[position] * 100));
    }

    private void highlight(int select) {
        if (speaker >= 0) {
            findViewById(speaker + 101).setBackgroundColor(Color.parseColor("#000000"));
            ((TextView) findViewById(speaker + 201)).setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (select >= 0) {
            findViewById(select + 101).setBackgroundColor(Color.parseColor("#FFFFFF"));
            ((TextView) findViewById(select + 201)).setTextColor(Color.parseColor("#000000"));
        }
    }

    private void createSpeakerList() {
        List<Speaker> list = Arrays.asList(config.getSystemType().getSpeakers());
        speakerList.setAdapter(new SpeakerListAdapter(getApplicationContext(), list, unsavedConfig, this::selectSpeaker, this::resetSpeakerPosition, this::editSpeakerInfo));
        speakerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        speakerList.setNestedScrollingEnabled(false);
    }

    private void changeSpeakerXBias(int speaker, float bias) {
        ConstraintSet set = new ConstraintSet();
        set.clone(speakerBorder);
        set.centerHorizontally(speaker + 101, speakerBorder.getId(), ConstraintSet.LEFT, 0, speakerBorder.getId(), ConstraintSet.RIGHT, 0, bias);
        set.applyTo(speakerBorder);
        unsavedConfig.xBiases[speaker] = bias;
        if (speaker == this.speaker) seekBarX.setProgress((int) (bias * 100));
    }

    private void changeSpeakerYBias(int speaker, float bias) {
        ConstraintSet set = new ConstraintSet();
        set.clone(speakerBorder);
        set.centerVertically(speaker + 101, speakerBorder.getId(), ConstraintSet.TOP, 0, speakerBorder.getId(), ConstraintSet.BOTTOM, 0, bias);
        set.applyTo(speakerBorder);
        unsavedConfig.yBiases[speaker] = bias;
        if (speaker == this.speaker) seekBarY.setProgress((int) (bias * 100));
    }

    private void resetSpeakerPosition(int speaker) {
        changeSpeakerXBias(speaker, config.getSystemType().getSpeakers()[speaker].getChannel().getDefaultXBias());
        changeSpeakerYBias(speaker, config.getSystemType().getSpeakers()[speaker].getChannel().getDefaultYBias());
    }

    private void resetAllSpeakerPositions() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditSpeakerLayout.this);
        alertDialogBuilder.setTitle(getString(R.string.resetAllSpeakerPositions));
        alertDialogBuilder.setMessage(getString(R.string.resetAllSpeakerPositionsDesc));
        alertDialogBuilder.setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
            for (int i = 0; i < config.getSystemType().getAmount(); i++) resetSpeakerPosition(i);
            Toast.makeText(this, getString(R.string.positionsReset), Toast.LENGTH_SHORT).show();
        });
        alertDialogBuilder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {});
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void editSpeakerInfo(int position) {
        Intent i = new Intent(this, EditSpeakerInfo.class);
        i.putExtra("position", position);
        startActivity(i);
    }

    private void saveLayout() {
        config = unsavedConfig.convertToStoredConfig(config);
        AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(config);
        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();
        finish();
    }
}