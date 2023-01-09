package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    public String name;
    protected int minRange, maxRange;
    public final Channel channel;
    public final int imageId;

    Speaker(String name, int minRange, int maxRange, Channel channel, int imageId) {
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.channel = channel;
        this.imageId = imageId;
    }
}
