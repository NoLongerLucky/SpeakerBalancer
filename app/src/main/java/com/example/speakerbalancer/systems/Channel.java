package com.example.speakerbalancer.systems;

public enum Channel {
    SPEAKER_FRONT_LEFT("FL", 0, 0, 0),
    SPEAKER_FRONT_RIGHT("FR", 1, 1, 0),
    SPEAKER_FRONT_CENTER("FC", 2, 0.5, 0),
    SPEAKER_LOW_FREQUENCY("LFE", 3, 0.5, 0.5),
    SPEAKER_BACK_LEFT("BL", 4, 0, 1),
    SPEAKER_BACK_RIGHT("BR", 5, 1, 1),
    SPEAKER_FRONT_LEFT_OF_CENTER("FLC", 6, 0.25, 0),
    SPEAKER_FRONT_RIGHT_OF_CENTER("FRC", 7, 0.75, 0),
    SPEAKER_BACK_CENTER("BC", 8, 0.5, 1),
    SPEAKER_SIDE_LEFT("SL", 9, 0, 0.5),
    SPEAKER_SIDE_RIGHT("SR", 10, 1, 0.5);

    public final String id;
    public final int index;
    public final double xBias, yBias;

    Channel(String id, int index, double xBias, double yBias) {
        this.id = id;
        this.index = index;
        this.xBias = xBias;
        this.yBias = yBias;
    }

    public double getxBias() {
        return xBias;
    }

    public double getyBias() {
        return yBias;
    }
}

// Information provided by a Wikipedia article
// https://en.wikipedia.org/wiki/Surround_sound#Standard_speaker_channels
