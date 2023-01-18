package com.example.speakerbalancer;

import android.graphics.Color;

public enum WallMaterial {
    DRYWALL("#C6C9Ca"),
    BRICK("#DC5539"),
    GLASS("#A8CCD7"),
    ACOUSTIC_PANELING("#5462C5");

    private final int color;

    WallMaterial(String color) {
        this.color = Color.parseColor(String.valueOf(color));
    }

    public String displayName() {
        String str = this.name();
        String[] split = str.split("_");
        str = "";
        for (String s : split) str = str.concat(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase() + " ");
        return str.trim();
    }

    public int getColor() {
        return color;
    }
}
