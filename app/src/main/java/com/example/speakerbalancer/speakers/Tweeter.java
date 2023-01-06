package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.range.Ranges;
import com.example.speakerbalancer.systems.Channel;

public class Tweeter extends Speaker {
    public Tweeter(Channel channel) {
        super(channel);
        name = "Tweeter";
        range = new Range(Ranges.MIDRANGE, Ranges.TWEETER);
    }
}
