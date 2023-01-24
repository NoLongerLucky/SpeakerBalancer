package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.TempConfig;

import java.util.Locale;

public class EditSpeakerInfo extends AppCompatActivity {
    EditText nameInput, minRangeInput, maxRangeInput;
    Button confirm;
    TempConfig c;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_speaker_info);

        nameInput = findViewById(R.id.nameInput);
        minRangeInput = findViewById(R.id.minRangeInput);
        maxRangeInput = findViewById(R.id.maxRangeInput);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(view -> saveData());

        c = EditSpeakerLayout.unsavedConfig;
        position = getIntent().getExtras().getInt("position");

        if (nameInput != null) nameInput.setText(c.names[position]);
        if (minRangeInput != null) minRangeInput.setText(String.format(Locale.getDefault(), "%d", c.minRanges[position]));
        if (maxRangeInput != null) maxRangeInput.setText(String.format(Locale.getDefault(), "%d", c.maxRanges[position]));
    }

    private void saveData() {
        String nameData = nameInput.getText().toString().trim();
        int minRangeData = Integer.parseInt(minRangeInput.getText().toString());
        int maxRangeData = Integer.parseInt(maxRangeInput.getText().toString());

        c.names[position] = nameData;
        c.minRanges[position] = minRangeData;
        c.maxRanges[position] = maxRangeData;

        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();

        finish();
    }
}