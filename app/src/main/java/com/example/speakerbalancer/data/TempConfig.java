package com.example.speakerbalancer.data;

import android.content.Context;

public class TempConfig {
    public float[] xBiases, yBiases;

    public TempConfig(Context context, int id) {
        StoredConfig storedConfig = AppDatabase.getDatabase(context).getDao().getData(id);

        this.xBiases = storedConfig.getSystemType().getxBiases();
        this.yBiases = storedConfig.getSystemType().getyBiases();
    }
}
