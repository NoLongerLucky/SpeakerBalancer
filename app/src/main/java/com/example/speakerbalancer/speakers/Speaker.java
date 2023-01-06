package com.example.speakerbalancer.speakers;

import com.example.speakerbalancer.range.Range;
import com.example.speakerbalancer.systems.Channel;

public class Speaker {
    protected String name;
    protected Range range;
    protected Channel channel;

    Speaker(Channel channel) {
        this.channel = channel;
    }
}
