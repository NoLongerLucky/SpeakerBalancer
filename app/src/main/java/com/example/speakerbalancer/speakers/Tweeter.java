package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Tweeter extends Speaker {
    public Tweeter(Channel channel) {
        super(channel);
        name = "Tweeter";
        minRange = 2000;
        maxRange = 20000;
    }
}
