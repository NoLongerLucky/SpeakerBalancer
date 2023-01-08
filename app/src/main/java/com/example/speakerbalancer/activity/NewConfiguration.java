package com.example.speakerbalancer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;
import com.example.speakerbalancer.systems.SpeakerSystem;
import com.example.speakerbalancer.systems.layouts.Quad;
import com.example.speakerbalancer.systems.layouts.Stereo;
import com.example.speakerbalancer.systems.layouts.Surround;

import java.util.Arrays;
import java.util.List;

public class NewConfiguration extends AppCompatActivity {
    EditText nameInput, roomLengthInput, roomWidthInput;
    Spinner systemTypeSpinner, wallMaterialSpinner;
    int systemTypeSpinnerId;
    Button confirm;
    List<SpeakerSystem> systemObjects;
    String[] systemNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_configuration);

        nameInput = findViewById(R.id.nameInput);
        systemTypeSpinner = findViewById(R.id.systemTypeSpinner);
        roomLengthInput = findViewById(R.id.roomLengthInput);
        roomWidthInput = findViewById(R.id.roomWidthInput);
        wallMaterialSpinner = findViewById(R.id.wallTypeSpinner);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(view -> saveData(-1));

        systemObjects = Arrays.asList(new Stereo(), new Surround(), new Quad());
        systemNames = new String[systemObjects.size()];
        for (SpeakerSystem system : systemObjects) {
            systemNames[systemObjects.indexOf(system)] = system.name;
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, systemNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        systemTypeSpinner.setAdapter(spinnerAdapter);

        systemTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                systemTypeSpinnerId = (int) id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                systemTypeSpinnerId = 0;
            }
        });
    }

    protected void saveData(int id) {
        String nameString = nameInput.getText().toString().trim();
        String systemTypeString = systemTypeSpinner.getSelectedItem().toString().trim();
        String roomLengthString = roomLengthInput.getText().toString();
        String roomWidthString = roomWidthInput.getText().toString();
        String wallMaterialString = wallMaterialSpinner.getSelectedItem().toString().trim();

        String errorMessage = getString(R.string.badInput);

        // Name not longer than 0 characters
        if (!(nameString.length() > 0)) errorMessage += addError(R.string.name, R.string.noInput);
        // Duplicate name
        else if (id != -1) if (AppDatabase.getDatabase(getApplicationContext()).getDao().getOtherNames(id).contains(nameString)) errorMessage += addError(R.string.name, R.string.nameMultiple);

        // Room length not larger than 0
        if (!(roomLengthString.length() > 0)) errorMessage += addError(R.string.roomLength, R.string.noInput);
        // Room length begins with 0
        else if (roomLengthString.startsWith("0")) errorMessage += addError(R.string.roomLength, R.string.zeroFirstDigit);

        // Room width not larger than 0
        if (!(roomWidthString.length() > 0)) errorMessage += addError(R.string.roomWidth, R.string.noInput);
        // Room width begins with 0
        else if (roomWidthString.startsWith("0")) errorMessage += addError(R.string.roomWidth, R.string.zeroFirstDigit);

        if (!errorMessage.equals(getString(R.string.badInput))) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        } else {
            confirmSuccess(nameString, wallMaterialString);
        }
    }

    protected void confirmSuccess(String nameData, String wallMaterialData) {
        SpeakerSystem systemTypeData = systemObjects.get(systemTypeSpinnerId);
        int roomLengthData = Integer.parseInt(roomLengthInput.getText().toString());
        int roomWidthData = Integer.parseInt(roomWidthInput.getText().toString());

        StoredConfig storedConfig = new StoredConfig();

        storedConfig.setName(nameData);
        storedConfig.setSystemType(systemTypeData);
        storedConfig.setRoomLength(roomLengthData);
        storedConfig.setRoomWidth(roomWidthData);
        storedConfig.setWallMaterial(wallMaterialData);

        AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(storedConfig);

        nameInput.setText("");
        systemTypeSpinner.setSelection(0);
        roomLengthInput.setText("");
        roomWidthInput.setText("");
        wallMaterialSpinner.setSelection(0);

        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();

        finish();

        Intent i = new Intent(NewConfiguration.this, EditConfiguration.class);
        i.putExtra("id", AppDatabase.getDatabase(getApplicationContext()).getDao().getNewId());
        startActivity(i);
    }

    protected String addError(@StringRes int string1, @StringRes int string2) {
        return "\n" + getString(string1) + getString(string2);
    }
}