package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    protected String name;
    protected int minRange, maxRange;
    protected Channel channel;

    Speaker(String name, int minRange, int maxRange, Channel channel) {
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.channel = channel;
    }
}
