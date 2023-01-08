package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.systems.Channel;

public class Subwoofer extends Speaker {
    public Subwoofer(Channel channel) {
        super("Subwoofer", 20, 200, channel);
    }
}
