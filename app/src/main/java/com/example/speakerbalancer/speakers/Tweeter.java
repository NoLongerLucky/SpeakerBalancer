package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.range.Ranges;

public class Tweeter extends Speaker {
    final String name = "Tweeter";
    final Range range = new Range(Ranges.MIDRANGE, Ranges.TWEETER);

    Tweeter(String name, Range range) {
        super(name, range);
    }
}
