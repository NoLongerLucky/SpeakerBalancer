package com.example.speakerbalancer.systems.layouts;

import com.example.speakerbalancer.speakers.Midrange;
import com.example.speakerbalancer.speakers.Subwoofer;
import com.example.speakerbalancer.speakers.Tweeter;
import com.example.speakerbalancer.systems.Channel;
import com.example.speakerbalancer.systems.SpeakerSystem;

public class Stereo extends SpeakerSystem {
    public Stereo() {
        super(3);
        lfe.setAllowed(true);

        speakers[0] = new Tweeter(Channel.SPEAKER_FRONT_LEFT);
        speakers[1] = new Tweeter(Channel.SPEAKER_FRONT_RIGHT);
        speakers[2] = new Midrange(Channel.SPEAKER_FRONT_CENTER);
        speakers[3] = new Subwoofer(Channel.SPEAKER_LOW_FREQUENCY);
    }
}
