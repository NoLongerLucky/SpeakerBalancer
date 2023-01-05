package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.range.Ranges;

public class Subwoofer extends Speaker {
    final String name = "Subwoofer";
    final Range range = new Range(Ranges.FLOOR, Ranges.SUBWOOFER);

    Subwoofer(String name, Range range) {
        super(name, range);
    }
}
