package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.range.Ranges;
import com.example.speakerbalancer.systems.Channel;

public class Subwoofer extends Speaker {
    public Subwoofer(Channel channel) {
        super(channel);
        name = "Subwoofer";
        range = new Range(Ranges.FLOOR, Ranges.SUBWOOFER);
    }
}
