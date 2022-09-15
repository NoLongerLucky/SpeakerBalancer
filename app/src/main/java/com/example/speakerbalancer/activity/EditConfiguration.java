package com.example.speakerbalancer.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;

public class EditConfiguration extends AppCompatActivity {
    int id;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_configuration);
        id = getIntent().getExtras().getInt("id");
        setData();

        name = findViewById(R.id.name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {
        StoredConfig config = AppDatabase.getDatabase(getApplicationContext()).getDao().getData(id);
        if (name != null) name.setText(config.getName());
    }
}