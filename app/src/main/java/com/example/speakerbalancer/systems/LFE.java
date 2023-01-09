package com.example.speakerbalancer.systems;

public class LFE {
    private boolean enabled, checked;

    public LFE(boolean enabled, boolean checked) {
        this.enabled = enabled;
        this.checked = checked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
