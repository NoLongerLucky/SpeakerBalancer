package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Ranges;
import com.example.speakerbalancer.systems.Channel;

public class Tweeter extends Speaker {
    public Tweeter(double xBias, double yBias, Channel channel) {
        super(xBias, yBias, channel);
        name = "Tweeter";
        range.setMin(Ranges.MIDRANGE);
        range.setMax(Ranges.TWEETER);
    }
}
