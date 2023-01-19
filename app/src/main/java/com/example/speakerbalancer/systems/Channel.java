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
    private float xBias, yBias;

    Channel(String id, int index, float xBias, float yBias) {
        this.id = id;
        this.index = index;
        this.xBias = xBias;
        this.yBias = yBias;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public float getxBias() {
        return xBias;
    }

    public void setxBias(float xBias) {
        this.xBias = xBias;
    }

    public float getyBias() {
        return yBias;
    }

    public void setyBias(float yBias) {
        this.yBias = yBias;
    }
}

// Information provided by a Wikipedia article
// https://en.wikipedia.org/wiki/Surround_sound#Standard_speaker_channels
