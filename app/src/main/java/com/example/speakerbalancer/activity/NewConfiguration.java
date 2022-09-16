package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class NewConfiguration extends AppCompatActivity {
    EditText name, roomLength, roomWidth;
    Spinner systemType, wallType;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_configuration);

        name = findViewById(R.id.name);
        systemType = findViewById(R.id.systemType);
        roomLength = findViewById(R.id.roomLength);
        roomWidth = findViewById(R.id.roomWidth);
        wallType = findViewById(R.id.wallType);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(view -> saveData());
    }

    private void saveData() {
        String name_txt = name.getText().toString().trim();
        String systemType_txt = systemType.getSelectedItem().toString().trim();
        String roomLength_txt = roomLength.getText().toString();
        String roomWidth_txt = roomWidth.getText().toString();
        String wallType_txt = wallType.getSelectedItem().toString().trim();

        String error_txt = getString(R.string.badInput);

        if (!(name_txt.length() > 0)) error_txt += addError(R.string.name, R.string.noInput);

        if (!(roomLength_txt.length() > 0)) error_txt += addError(R.string.roomLength, R.string.noInput);
        else if (roomLength_txt.startsWith("0")) error_txt += addError(R.string.roomLength, R.string.zeroFirstDigit);

        if (!(roomWidth_txt.length() > 0)) error_txt += addError(R.string.roomWidth, R.string.noInput);
        else if (roomWidth_txt.startsWith("0")) error_txt += addError(R.string.roomWidth, R.string.zeroFirstDigit);

        if (!error_txt.equals(getString(R.string.badInput))) {
            Toast.makeText(this, error_txt, Toast.LENGTH_SHORT).show();
        } else {
            confirmSuccess(name_txt, systemType_txt, wallType_txt);
        }
    }

    protected void confirmSuccess(String name_txt, String systemType_txt, String wallType_txt) {
        int roomLength_num = Integer.parseInt(roomLength.getText().toString());
        int roomWidth_num = Integer.parseInt(roomWidth.getText().toString());

        StoredConfig storedConfig = new StoredConfig();

        storedConfig.setName(name_txt);
        storedConfig.setSystemType(systemType_txt);
        storedConfig.setRoomLength(roomLength_num);
        storedConfig.setRoomWidth(roomWidth_num);
        storedConfig.setWallType(wallType_txt);

        AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(storedConfig);

        name.setText("");
        systemType.setSelection(0);
        roomLength.setText("");
        roomWidth.setText("");
        wallType.setSelection(0);

        Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();
    }

    protected String addError(@StringRes int string1, @StringRes int string2) {
        return "\n" + getString(string1) + getString(string2);
    }
}