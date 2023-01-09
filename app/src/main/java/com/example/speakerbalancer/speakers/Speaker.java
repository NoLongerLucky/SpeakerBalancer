package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    public String name;
    protected int minRange, maxRange;
    public final Channel channel;

    Speaker(String name, int minRange, int maxRange, Channel channel) {
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.channel = channel;
    }
}
