package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.range.Ranges;

public class Midrange extends Speaker {
    final String name = "Midrange";
    final Range range = new Range(Ranges.SUBWOOFER, Ranges.MIDRANGE);

    Midrange(String name, Range range) {
        super(name, range);
    }
}
