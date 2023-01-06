package com.example.speakerbalancer.systems;

import com.example.speakerbalancer.speakers.Speaker;

public class SpeakerSystem {
    protected int amount;
    protected Speaker[] speakers;
    protected LFE lfe = new LFE();

    public SpeakerSystem(int amount) {
        this.amount = amount;
        this.speakers = new Speaker[amount + 1];
    }

    protected String notation() {
        return amount + "." + (lfe.isEnabled() ? 1 : 0);
    }
}
