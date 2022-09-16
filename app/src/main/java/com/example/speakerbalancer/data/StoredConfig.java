package com.example.speakerbalancer.data;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.speakerbalancer.R;

import java.util.Arrays;

@Entity(tableName = "config_table")
public class StoredConfig {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "system_type")
    public String systemType;

    @ColumnInfo(name = "room_length")
    public int roomLength;

    @ColumnInfo(name = "room_width")
    public int roomWidth;

    @ColumnInfo(name = "wall_type")
    public String wallType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemType() {
        return systemType;
    }

    public int getSystemTypeFromArray(Context context, String systemType) {
        String[] systemTypes = context.getResources().getStringArray(R.array.systemTypes);
        return Arrays.asList(systemTypes).indexOf(systemType);
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public int getRoomLength() {
        return roomLength;
    }

    public void setRoomLength(int roomLength) {
        this.roomLength = roomLength;
    }

    public int getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(int roomWidth) {
        this.roomWidth = roomWidth;
    }

    public String getWallType() {
        return wallType;
    }

    public int getWallTypeFromArray(Context context, String wallType) {
        String[] wallTypes = context.getResources().getStringArray(R.array.wallTypes);
        return Arrays.asList(wallTypes).indexOf(wallType);
    }

    public void setWallType(String wallType) {
        this.wallType = wallType;
    }
}
