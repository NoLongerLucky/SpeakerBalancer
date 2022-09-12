package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class NewConfiguration extends AppCompatActivity {
    EditText name, roomLength, roomWidth;
    Spinner systemType;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_configuration);

        name = findViewById(R.id.name);
        systemType = findViewById(R.id.systemType);
        roomLength = findViewById(R.id.roomLength);
        roomWidth = findViewById(R.id.roomWidth);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        String name_txt = name.getText().toString().trim();
        String systemType_txt = systemType.getSelectedItem().toString().trim();
        String roomLength_txt = roomLength.getText().toString();
        String roomWidth_txt = roomWidth.getText().toString();

        if (name_txt.length() > 0 && roomLength_txt.length() > 0 && roomWidth_txt.length() > 0) {
            int roomLength_num = Integer.parseInt(roomLength.getText().toString());
            int roomWidth_num = Integer.parseInt(roomWidth.getText().toString());

            StoredConfig storedConfig = new StoredConfig();

            storedConfig.setName(name_txt);
            storedConfig.setSystemType(systemType_txt);
            storedConfig.setRoomLength(roomLength_num);
            storedConfig.setRoomWidth(roomWidth_num);

            AppDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(storedConfig);

            name.setText("");
            systemType.setSelection(0);
            roomLength.setText("");
            roomWidth.setText("");

            Toast.makeText(this, getString(R.string.dataSaved), Toast.LENGTH_SHORT).show();
        } else {
            String error_txt = getString(R.string.badInput);
            if (!(name_txt.length() > 0)) error_txt += "\n" + getString(R.string.name) + getString(R.string.noInput);
            if (!(roomLength_txt.length() > 0)) error_txt += "\n" + getString(R.string.roomLength) + getString(R.string.noInput);
            if (!(roomWidth_txt.length() > 0)) error_txt += "\n" + getString(R.string.roomLength) + getString(R.string.noInput);
            Toast.makeText(this, error_txt, Toast.LENGTH_SHORT).show();
        }
    }
}