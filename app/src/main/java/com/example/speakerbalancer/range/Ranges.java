package com.example.speakerbalancer.range;

public enum Ranges {
    FLOOR(20),
    SUBWOOFER(200),
    MIDRANGE(2000),
    TWEETER(20000);

    public final int value;

    Ranges(int value) {
        this.value = value;
    }
}
