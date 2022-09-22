package com.example.speakerbalancer.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StoredConfigDao {
    @Insert(onConflict = REPLACE)
    void insertAllData(StoredConfig storedConfig);

    @Query("SELECT * FROM config_table")
    List<StoredConfig> getAllData();

    @Query("SELECT * FROM config_table WHERE `id` = :id")
    StoredConfig getData(int id);

    @Query("DELETE FROM config_table WHERE `id` = :id")
    void deleteData(int id);

    @Query("SELECT * FROM config_table ORDER BY id DESC LIMIT 1")
    int getNewId();

    @Query("SELECT name FROM config_table WHERE id != :id")
    List<String> getOtherNames(int id);
}
