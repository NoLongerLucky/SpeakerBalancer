package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Midrange extends Speaker {
    public Midrange(Channel channel) {
        super(channel);
        name = "Midrange";
        minRange = 200;
        maxRange = 2000;
    }
}
