package com.example.speakerbalancer.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.data.AppDatabase;
import com.example.speakerbalancer.data.StoredConfig;
import com.example.speakerbalancer.data.StoredConfigAdapter;

import java.util.List;

public class LoadConfiguration extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView noConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_configuration);
        recyclerView = findViewById(R.id.recycler_view);
        noConfig = findViewById(R.id.noConfig);

        getData();
        updateTextVisibility();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void updateTextVisibility() {
        int vis = View.INVISIBLE;
        if (recyclerView.getAdapter() != null) if (recyclerView.getAdapter().getItemCount() == 0) vis = View.VISIBLE;
        noConfig.setVisibility(vis);
    }

    private void getData() {
        List<StoredConfig> list = AppDatabase.getDatabase(getApplicationContext()).getDao().getAllData();
        recyclerView.setAdapter(new StoredConfigAdapter(getApplicationContext(), list, (position, id) -> {
            Intent i = new Intent(LoadConfiguration.this, EditConfiguration.class);
            i.putExtra("id", id);
            startActivity(i);
        }, (position, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoadConfiguration.this);
            alertDialogBuilder.setTitle(getString(R.string.delConfig));
            alertDialogBuilder.setMessage(getString(R.string.delConfigMsg));
            alertDialogBuilder.setPositiveButton(getString(R.string.confirm),
                    (dialog, which) -> {
                        AppDatabase.getDatabase(getApplicationContext()).getDao().deleteData(id);
                        getData();
                        updateTextVisibility();
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