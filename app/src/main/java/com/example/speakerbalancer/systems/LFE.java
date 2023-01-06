package com.example.speakerbalancer.systems;

public class LFE {
    private boolean allowed, forced, enabled;

    public LFE() {
        this.allowed = this.forced = this.enabled = false;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public boolean isForced() {
        return forced;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    public boolean isEnabled() {
        return enabled || forced;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
