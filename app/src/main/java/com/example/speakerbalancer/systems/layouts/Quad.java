package com.example.speakerbalancer.systems.layouts;

import com.example.speakerbalancer.speakers.Tweeter;
import com.example.speakerbalancer.systems.Channel;
import com.example.speakerbalancer.systems.SpeakerSystem;

public class Quad extends SpeakerSystem {
    public Quad() {
        super(4);
        lfe.setAllowed(false);

        speakers[0] = new Tweeter(Channel.SPEAKER_FRONT_LEFT);
        speakers[1] = new Tweeter(Channel.SPEAKER_FRONT_RIGHT);
        speakers[2] = new Tweeter(Channel.SPEAKER_BACK_LEFT);
        speakers[3] = new Tweeter(Channel.SPEAKER_BACK_RIGHT);
    }
}
