package com.example.speakerbalancer.data;

import android.content.Context;

public class TempConfig {
    public float[] xBiases, yBiases;

    public TempConfig(Context context, int id) {
        StoredConfig storedConfig = AppDatabase.getDatabase(context).getDao().getData(id);

        this.xBiases = storedConfig.getSystemType().getXBiases();
        this.yBiases = storedConfig.getSystemType().getYBiases();
    }

    public StoredConfig convertToStoredConfig(StoredConfig config) {
        config.getSystemType().setXBiases(xBiases);
        config.getSystemType().setYBiases(yBiases);
        return config;
    }

    public int amount() {
        return Math.min(xBiases.length, yBiases.length);
    }
}
