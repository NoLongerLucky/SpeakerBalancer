package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Subwoofer extends Speaker {
    public Subwoofer(Channel channel) {
        super(channel);
        name = "Subwoofer";
        minRange = 20;
        maxRange = 200;
    }
}
