package com.example.speakerbalancer.data;

import android.content.Context;

public class TempConfig {
    StoredConfig config;
    public double[] xBiases, yBiases;

    public TempConfig(Context context, int id) {
        config = AppDatabase.getDatabase(context).getDao().getData(id);

        this.xBiases = config.getSystemType().getxBiases();
        this.yBiases = config.getSystemType().getyBiases();
    }
}
