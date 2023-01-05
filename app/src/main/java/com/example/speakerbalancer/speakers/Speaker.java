package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;

public abstract class Speaker {
    protected String name;
    protected Range range;

    Speaker(String name, Range range) {
        this.name = name;
        this.range = range;
    }
}
