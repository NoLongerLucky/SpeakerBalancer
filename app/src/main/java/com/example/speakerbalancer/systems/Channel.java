package com.example.speakerbalancer.systems;

public enum Channel {
    SPEAKER_FRONT_LEFT("FL", 0),
    SPEAKER_FRONT_RIGHT("FR", 1),
    SPEAKER_FRONT_CENTER("FC", 2),
    SPEAKER_LOW_FREQUENCY("LFE", 3),
    SPEAKER_BACK_LEFT("BL", 4),
    SPEAKER_BACK_RIGHT("BR", 5),
    SPEAKER_FRONT_LEFT_OF_CENTER("FLC", 6),
    SPEAKER_FRONT_RIGHT_OF_CENTER("FRC", 7),
    SPEAKER_BACK_CENTER("BC", 8),
    SPEAKER_SIDE_LEFT("SL", 9),
    SPEAKER_SIDE_RIGHT("SR", 10),
    SPEAKER_TOP_CENTER("TC", 11),
    SPEAKER_TOP_FRONT_LEFT("TFL", 12),
    SPEAKER_TOP_FRONT_CENTER("TFC", 13),
    SPEAKER_TOP_FRONT_RIGHT("TFR", 14),
    SPEAKER_TOP_BACK_LEFT("TBL", 15),
    SPEAKER_TOP_BACK_CENTER("TBC", 16),
    SPEAKER_TOP_BACK_RIGHT("TBR", 17);

    public final String id;
    public final int index;

    Channel(String id, int index) {
        this.id = id;
        this.index = index;
    }
}

// Information provided by a Wikipedia article
// https://en.wikipedia.org/wiki/Surround_sound#Standard_speaker_channels
