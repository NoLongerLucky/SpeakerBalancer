package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    private String name;
    private int minRange, maxRange;
    private final Channel channel;
    private final int imageId;

    Speaker(String name, int minRange, int maxRange, Channel channel, int imageId) {
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.channel = channel;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getImageId() {
        return imageId;
    }
}
