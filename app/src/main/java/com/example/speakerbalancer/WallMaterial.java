package com.example.speakerbalancer;

public enum WallMaterial {
    DRYWALL,
    BRICK,
    GLASS,
    ACOUSTIC_PANELING;

    public String displayName() {
        String str = this.name();
        String[] split = str.split("_");
        str = "";
        for (String s : split) str = str.concat(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase() + " ");
        return str.trim();
    }
}
