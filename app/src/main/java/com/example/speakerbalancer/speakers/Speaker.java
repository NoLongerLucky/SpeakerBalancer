package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    private String name;
    private int minRange, maxRange;
    private final Channel channel;
    private float xBias, yBias;
    private final int imageId;

    Speaker(String name, int minRange, int maxRange, Channel channel, int imageId) {
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.channel = channel;
        this.xBias = channel.getDefaultXBias();
        this.yBias = channel.getDefaultYBias();
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

    public float getXBias() {
        return xBias;
    }

    public void setXBias(float xBias) {
        this.xBias = xBias;
    }

    public float getYBias() {
        return yBias;
    }

    public void setYBias(float yBias) {
        this.yBias = yBias;
    }

    public int getImageId() {
        return imageId;
    }
}
