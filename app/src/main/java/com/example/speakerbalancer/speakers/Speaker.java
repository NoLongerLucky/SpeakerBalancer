package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    protected String name;
    protected Range range;
    protected double xBias, yBias;
    protected Channel channel;

    Speaker(double xBias, double yBias, Channel channel) {
        this.xBias = xBias;
        this.yBias = yBias;
        this.channel = channel;
    }
}
