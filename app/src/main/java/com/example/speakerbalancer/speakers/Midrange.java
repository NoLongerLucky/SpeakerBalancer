package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Midrange extends Speaker {
    public Midrange(Channel channel) {
        super("Midrange", 200, 2000, channel);
    }
}
