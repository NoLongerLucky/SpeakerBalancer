package com.example.speakerbalancer.systems.layouts;

import com.example.speakerbalancer.speakers.Subwoofer;
import com.example.speakerbalancer.speakers.Tweeter;
import com.example.speakerbalancer.systems.Channel;
import com.example.speakerbalancer.systems.SpeakerSystem;

public class Stereo extends SpeakerSystem {
    public Stereo() {
        super(3);
        lfe.setAllowed(true);

        speakers[0] = new Tweeter(0, 0, Channel.SPEAKER_FRONT_LEFT);
        speakers[1] = new Tweeter(1, 0, Channel.SPEAKER_FRONT_RIGHT);
        speakers[2] = new Subwoofer(0.5, 0.5, Channel.SPEAKER_LOW_FREQUENCY);
    }
}
