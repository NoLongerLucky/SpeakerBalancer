package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    protected String name;
    protected int minRange, maxRange;
    protected Channel channel;

    Speaker(Channel channel) {
        this.channel = channel;
    }
}
