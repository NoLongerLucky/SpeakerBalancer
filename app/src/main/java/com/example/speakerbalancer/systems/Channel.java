package com.example.speakerbalancer.systems;

public enum Channel {
    SPEAKER_FRONT_LEFT("FL", 0, 0F, 0F),
    SPEAKER_FRONT_RIGHT("FR", 1, 1F, 0F),
    SPEAKER_FRONT_CENTER("FC", 2, 0.5F, 0F),
    SPEAKER_LOW_FREQUENCY("LFE", 3, 0.5F, 0.5F),
    SPEAKER_BACK_LEFT("BL", 4, 0F, 1F),
    SPEAKER_BACK_RIGHT("BR", 5, 1F, 1F),
    SPEAKER_FRONT_LEFT_OF_CENTER("FLC", 6, 0.25F, 0F),
    SPEAKER_FRONT_RIGHT_OF_CENTER("FRC", 7, 0.75F, 0F),
    SPEAKER_BACK_CENTER("BC", 8, 0.5F, 1F),
    SPEAKER_SIDE_LEFT("SL", 9, 0F, 0.5F),
    SPEAKER_SIDE_RIGHT("SR", 10, 1F, 0.5F);

    private final String id;
    private final int index;
    private final float defaultXBias, defaultYBias;

    Channel(String id, int index, float defaultXBias, float defaultYBias) {
        this.id = id;
        this.index = index;
        this.defaultXBias = defaultXBias;
        this.defaultYBias = defaultYBias;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public float getDefaultXBias() {
        return defaultXBias;
    }

    public float getDefaultYBias() {
        return defaultYBias;
    }
}

// Information provided by a Wikipedia article
// https://en.wikipedia.org/wiki/Surround_sound#Standard_speaker_channels
