package com.example.speakerbalancer.systems;

import com.example.speakerbalancer.systems.layouts.Quad;
import com.example.speakerbalancer.systems.layouts.Stereo;
import com.example.speakerbalancer.systems.layouts.Surround;

public enum SystemDirectory {
    STEREO(new Stereo()),
    SURROUND(new Surround()),
    QUAD(new Quad());

    public final SpeakerSystem speakerSystem;

    SystemDirectory(SpeakerSystem speakerSystem) {
        this.speakerSystem = speakerSystem;
    }
}
