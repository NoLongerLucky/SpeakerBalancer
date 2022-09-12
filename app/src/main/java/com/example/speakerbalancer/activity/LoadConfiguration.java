package com.example.speakerbalancer.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

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
        recyclerView.setAdapter(new StoredConfigAdapter(getApplicationContext(), list, (position, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoadConfiguration.this);
            alertDialogBuilder.setTitle(getString(R.string.delConfig));
            alertDialogBuilder.setMessage(getString(R.string.delConfigMsg));
            alertDialogBuilder.setPositiveButton(getString(R.string.confirm),
                    (dialog, which) -> {
                        AppDatabase.getDatabase(getApplicationContext()).getDao().deleteData(id);
                        getData();
                        Toast.makeText(LoadConfiguration.this, getString(R.string.delYes), Toast.LENGTH_SHORT).show();
                    });
            alertDialogBuilder.setNegativeButton(getString(R.string.cancel),
                    (dialog, which) -> Toast.makeText(LoadConfiguration.this, getString(R.string.delNo), Toast.LENGTH_SHORT).show());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}