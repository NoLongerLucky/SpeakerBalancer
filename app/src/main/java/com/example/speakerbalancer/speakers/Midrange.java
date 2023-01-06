package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.range.Ranges;
import com.example.speakerbalancer.systems.Channel;

public class Midrange extends Speaker {
    public Midrange(Channel channel) {
        super(channel);
        name = "Midrange";
        range = new Range(Ranges.SUBWOOFER, Ranges.MIDRANGE);
    }
}
