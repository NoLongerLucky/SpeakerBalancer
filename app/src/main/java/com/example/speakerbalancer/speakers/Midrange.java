package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Ranges;
import com.example.speakerbalancer.systems.Channel;

public class Midrange extends Speaker {
    public Midrange(double xBias, double yBias, Channel channel) {
        super(xBias, yBias, channel);
        name = "Midrange";
        range.setMin(Ranges.SUBWOOFER);
        range.setMax(Ranges.MIDRANGE);
    }
}
