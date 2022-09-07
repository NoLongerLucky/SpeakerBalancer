package com.example.speakerbalancer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.appsearch.StorageInfo;
import android.os.Bundle;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;
import com.example.speakerbalancer.data.StoredConfigAdapter;
import com.example.speakerbalancer.data.StoredConfigDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoadConfiguration extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_configuration);
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        recyclerView.setAdapter(new StoredConfigAdapter(getApplicationContext(), (List<StoredConfig>) AppDatabase.getDatabase(getApplicationContext())));
    }
}