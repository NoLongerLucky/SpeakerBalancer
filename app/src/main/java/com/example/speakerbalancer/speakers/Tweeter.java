package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.R;
import com.example.speakerbalancer.systems.Channel;

public class Tweeter extends Speaker {
    public Tweeter(Channel channel) {
        super("Tweeter", 2000, 20000, channel, R.drawable.tweeter);
    }
}
