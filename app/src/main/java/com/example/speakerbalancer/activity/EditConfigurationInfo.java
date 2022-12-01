package com.example.speakerbalancer.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class EditConfigurationInfo extends NewConfiguration {
    int id;
    StoredConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getExtras().getInt("id");
        confirm.setOnClickListener(view -> saveData(id));

        config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);

        if (name != null) name.setText(config.getName());
        systemType.setSelection(config.getSystemTypeFromArray(getApplicationContext(), config.getSystemType()));
        if (roomLength != null) roomLength.setText(String.valueOf(config.getRoomLength()));
        if (roomWidth != null) roomWidth.setText(String.valueOf(config.getRoomWidth()));
        wallType.setSelection(config.getWallTypeFromArray(getApplicationContext(), config.getWallType()));
    }

    protected void confirmSuccess(String name_txt, String systemType_txt, String wallType_txt) {
        if (!config.getSystemType().equals(systemType_txt)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditConfigurationInfo.this);
            alertDialogBuilder.setTitle(getString(R.string.changedSystem));
            alertDialogBuilder.setMessage(getString(R.string.changedSystemBody));
            alertDialogBuilder.setPositiveButton(getString(R.string.confirm),
                    (dialog, which) -> {
                        // Resetting speaker positions will go here
                        saveChanges(name_txt, systemType_txt, wallType_txt);
                    });
            alertDialogBuilder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {});
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else saveChanges(name_txt, systemType_txt, wallType_txt);
    }

    private void saveChanges(String name_txt, String systemType_txt, String wallType_txt) {
        int roomLength_num = Integer.parseInt(roomLength.getText().toString());
        int roomWidth_num = Integer.parseInt(roomWidth.getText().toString());

        StoredConfig storedConfig = new StoredConfig();

        storedConfig.setId(id);
        storedConfig.setName(name_txt);
        storedConfig.setSystemType(systemType_txt);
        storedConfig.setRoomLength(roomLength_num);
        storedConfig.setRoomWidth(roomWidth_num);
        storedConfig.setWallType(wallType_txt);

        AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(storedConfig);

        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();

        finish();
    }
}