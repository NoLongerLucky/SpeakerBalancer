package com.example.speakerbalancer.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StoredConfig.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StoredConfigDao getDao();
    private static AppDatabase instance;

    public static AppDatabase getDatabase(Context context) {
        if (instance != null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "DATABASE").build();
        }
        return instance;
    }
}