package com.example.speakerbalancer.data;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StoredConfigDao {
    @Insert(onConflict = REPLACE)
    void insertAllData(StoredConfig storedConfig);

    @Query("SELECT * FROM config_table")
    List<StoredConfig> getAllData();
}
