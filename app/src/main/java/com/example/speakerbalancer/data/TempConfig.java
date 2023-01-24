package com.example.speakerbalancer.data;

import android.content.Context;

public class TempConfig {
    public String[] names;
    public int[] minRanges, maxRanges;
    public float[] xBiases, yBiases;

    public TempConfig(Context context, int id) {
        StoredConfig storedConfig = AppDatabase.getDatabase(context).getDao().getData(id);

        this.names = storedConfig.getSystemType().getNames();
        this.minRanges = storedConfig.getSystemType().getMinRanges();
        this.maxRanges = storedConfig.getSystemType().getMaxRanges();
        this.xBiases = storedConfig.getSystemType().getXBiases();
        this.yBiases = storedConfig.getSystemType().getYBiases();
    }

    public StoredConfig convertToStoredConfig(StoredConfig config) {
        config.getSystemType().setNames(names);
        config.getSystemType().setMinRanges(minRanges);
        config.getSystemType().setMaxRanges(maxRanges);
        config.getSystemType().setXBiases(xBiases);
        config.getSystemType().setYBiases(yBiases);
        return config;
    }
}
