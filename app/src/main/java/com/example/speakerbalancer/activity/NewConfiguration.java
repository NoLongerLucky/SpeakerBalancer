package com.example.speakerbalancer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.WallMaterial;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;
import com.example.speakerbalancer.systems.LFE;
import com.example.speakerbalancer.systems.SpeakerSystem;
import com.example.speakerbalancer.systems.SystemDirectory;

public class NewConfiguration extends AppCompatActivity {
    EditText nameInput, roomLengthInput, roomWidthInput;
    Spinner systemTypeSpinner, wallMaterialSpinner;
    CheckBox lfeCheckbox;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_configuration);

        nameInput = findViewById(R.id.nameInput);
        systemTypeSpinner = findViewById(R.id.systemTypeSpinner);
        lfeCheckbox = findViewById(R.id.lfeCheckbox);
        roomLengthInput = findViewById(R.id.roomLengthInput);
        roomWidthInput = findViewById(R.id.roomWidthInput);
        wallMaterialSpinner = findViewById(R.id.wallMaterialSpinner);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(view -> saveData(-1));

        ArrayAdapter<SystemDirectory> systemTypeSpinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, SystemDirectory.values());
        systemTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        systemTypeSpinner.setAdapter(systemTypeSpinnerAdapter);

        ArrayAdapter<WallMaterial> wallMaterialSpinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, WallMaterial.values());
        wallMaterialSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wallMaterialSpinner.setAdapter(wallMaterialSpinnerAdapter);

        systemTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LFE lfe = ((SystemDirectory) systemTypeSpinner.getSelectedItem()).getSpeakerSystem().getLfe();
                updateCheckbox(lfe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    protected void updateCheckbox(LFE lfe) {
        lfeCheckbox.setEnabled(lfe.isEnabled());
        lfeCheckbox.setChecked(lfe.isChecked() || (lfe.isEnabled() && lfeCheckbox.isChecked()));
    }

    protected void saveData(int id) {
        String nameString = nameInput.getText().toString().trim();
        // String systemTypeString = systemTypeSpinner.getSelectedItem().toString().trim();
        String roomLengthString = roomLengthInput.getText().toString();
        String roomWidthString = roomWidthInput.getText().toString();
        // String wallMaterialString = wallMaterialSpinner.getSelectedItem().toString().trim();

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
            confirmSuccess();
        }
    }

    protected void confirmSuccess() {
        SpeakerSystem systemTypeData = ((SystemDirectory) systemTypeSpinner.getSelectedItem()).getSpeakerSystem();
        saveToDatabase(systemTypeData, -1);

        Intent i = new Intent(NewConfiguration.this, EditConfiguration.class);
        i.putExtra("id", AppDatabase.getDatabase(getApplicationContext()).getDao().getNewId());
        startActivity(i);
    }

    protected void saveToDatabase(SpeakerSystem systemTypeData, int id) {
        String nameData = nameInput.getText().toString().trim();
        int roomLengthData = Integer.parseInt(roomLengthInput.getText().toString());
        int roomWidthData = Integer.parseInt(roomWidthInput.getText().toString());
        WallMaterial wallMaterialData = (WallMaterial) wallMaterialSpinner.getSelectedItem();

        StoredConfig storedConfig = new StoredConfig();

        if (id >= 0) storedConfig.setId(id);
        storedConfig.setName(nameData);
        storedConfig.setSystemType(systemTypeData);
        storedConfig.getSystemType().getLfe().setChecked(lfeCheckbox.isChecked());
        storedConfig.setRoomLength(roomLengthData);
        storedConfig.setRoomWidth(roomWidthData);
        storedConfig.setWallMaterial(wallMaterialData);

        AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(storedConfig);

        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();

        finish();
    }

    protected String addError(@StringRes int string1, @StringRes int string2) {
        return "\n" + getString(string1) + getString(string2);
    }
}