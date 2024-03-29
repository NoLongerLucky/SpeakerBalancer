package com.example.speakerbalancer.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.speakerbalancer.WallMaterial;
import com.example.speakerbalancer.systems.SpeakerSystem;

@Entity(tableName = "config_table")
public class StoredConfig {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "system_type")
    private SpeakerSystem systemType;

    @ColumnInfo(name = "room_length")
    private int roomLength;

    @ColumnInfo(name = "room_width")
    private int roomWidth;

    @ColumnInfo(name = "wall_material")
    private WallMaterial wallMaterial;

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

    public SpeakerSystem getSystemType() {
        return systemType;
    }

    public void setSystemType(SpeakerSystem systemType) {
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

    public WallMaterial getWallMaterial() {
        return wallMaterial;
    }

    public void setWallMaterial(WallMaterial wallMaterial) {
        this.wallMaterial = wallMaterial;
    }
}
