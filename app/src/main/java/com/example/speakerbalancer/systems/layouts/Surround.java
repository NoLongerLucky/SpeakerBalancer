package com.example.speakerbalancer.systems.layouts;

import com.example.speakerbalancer.speakers.Midrange;
import com.example.speakerbalancer.speakers.Tweeter;
import com.example.speakerbalancer.systems.Channel;
import com.example.speakerbalancer.systems.SpeakerSystem;

public class Surround extends SpeakerSystem {
    public Surround() {
        super(3, "Surround", false, false);

        speakers[0] = new Tweeter(Channel.SPEAKER_FRONT_LEFT);
        speakers[1] = new Tweeter(Channel.SPEAKER_FRONT_RIGHT);
        speakers[2] = new Midrange(Channel.SPEAKER_BACK_CENTER);
    }
}
