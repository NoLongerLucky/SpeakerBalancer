package com.example.speakerbalancer.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StoredConfig.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StoredConfigDao getDao();
    private static AppDatabase instance;

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = Room.databaseBuilder(context, AppDatabase.class, "DATABASE").allowMainThreadQueries().build();
            }
        }
        return instance;
    }
}