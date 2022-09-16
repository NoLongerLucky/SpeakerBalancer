package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class EditConfigurationInfo extends NewConfiguration {
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getExtras().getInt("id");

        StoredConfig config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);

        if (name != null) name.setText(config.getName());
        systemType.setSelection(config.getSystemTypeFromArray(getApplicationContext(), config.getSystemType()));
        if (roomLength != null) roomLength.setText(String.valueOf(config.getRoomLength()));
        if (roomWidth != null) roomWidth.setText(String.valueOf(config.getRoomWidth()));
        wallType.setSelection(config.getWallTypeFromArray(getApplicationContext(), config.getWallType()));
    }

    protected void confirmSuccess(String name_txt, String systemType_txt, String wallType_txt) {
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