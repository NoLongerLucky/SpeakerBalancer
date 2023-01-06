package com.example.speakerbalancer.systems;

import com.example.speakerbalancer.speakers.Speaker;

public class SpeakerSystem {
    protected int amount;
    public String name;
    protected Speaker[] speakers;
    protected LFE lfe = new LFE();

    public SpeakerSystem(int amount, String name, boolean allowLFE) {
        lfe.setAllowed(allowLFE);
        this.amount = amount;
        this.name = name;
        this.speakers = new Speaker[amount + (lfe.isAllowed() ? 1 : 0)];
    }

    protected String notation() {
        return amount + "." + (lfe.isEnabled() ? 1 : 0);
    }
}
