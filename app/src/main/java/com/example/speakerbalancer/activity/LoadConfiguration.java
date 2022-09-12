package com.example.speakerbalancer.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;
import com.example.speakerbalancer.data.StoredConfigAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoadConfiguration extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<StoredConfig> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_configuration);
        recyclerView = findViewById(R.id.recycler_view);

        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        list = new ArrayList<>();
        list = AppDatabase.getDatabase(getApplicationContext()).getDao().getAllData();
        recyclerView.setAdapter(new StoredConfigAdapter(getApplicationContext(), list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}