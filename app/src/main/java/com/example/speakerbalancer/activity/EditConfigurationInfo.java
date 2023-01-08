package com.example.speakerbalancer.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;
import com.example.speakerbalancer.systems.SpeakerSystem;

import java.util.Arrays;

public class EditConfigurationInfo extends NewConfiguration {
    int id;
    StoredConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getExtras().getInt("id");
        confirm.setOnClickListener(view -> saveData(id));

        config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);

        if (nameInput != null) nameInput.setText(config.getName());
        systemTypeSpinner.setSelection(Arrays.asList(systemNames).indexOf(config.getSystemType().name));
        if (roomLengthInput != null) roomLengthInput.setText(String.valueOf(config.getRoomLength()));
        if (roomWidthInput != null) roomWidthInput.setText(String.valueOf(config.getRoomWidth()));
        wallMaterialSpinner.setSelection(config.getWallTypeFromArray(getApplicationContext(), config.getWallMaterial()));
    }

    protected void confirmSuccess(String nameData, String wallMaterialData) {
        SpeakerSystem systemTypeData = systemObjects.get(systemTypeSpinnerId);
        if (!config.getSystemType().name.equals(systemTypeData.name)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditConfigurationInfo.this);
            alertDialogBuilder.setTitle(getString(R.string.changedSystem));
            alertDialogBuilder.setMessage(getString(R.string.changedSystemBody));
            alertDialogBuilder.setPositiveButton(getString(R.string.confirm),
                    (dialog, which) -> {
                        // Resetting speaker positions will go here
                        saveChanges(nameData, systemTypeData, wallMaterialData);
                    });
            alertDialogBuilder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {});
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else saveChanges(nameData, systemTypeData, wallMaterialData);
    }

    private void saveChanges(String nameData, SpeakerSystem systemTypeData, String wallMaterialData) {
        int roomLengthData = Integer.parseInt(roomLengthInput.getText().toString());
        int roomWidthData = Integer.parseInt(roomWidthInput.getText().toString());

        StoredConfig storedConfig = new StoredConfig();

        storedConfig.setId(id);
        storedConfig.setName(nameData);
        storedConfig.setSystemType(systemTypeData);
        storedConfig.setRoomLength(roomLengthData);
        storedConfig.setRoomWidth(roomWidthData);
        storedConfig.setWallMaterial(wallMaterialData);

        AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(storedConfig);

        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();

        finish();
    }
}