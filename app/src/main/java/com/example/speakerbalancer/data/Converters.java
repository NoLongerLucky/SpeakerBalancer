package com.example.speakerbalancer.data;

import androidx.room.TypeConverter;

import com.example.speakerbalancer.systems.SpeakerSystem;
import com.google.gson.Gson;

public class Converters {
    @TypeConverter
    public SpeakerSystem fromString(String value) {
        return new Gson().fromJson(value, SpeakerSystem.class);
    }

    @TypeConverter
    public static String fromObject(SpeakerSystem obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
