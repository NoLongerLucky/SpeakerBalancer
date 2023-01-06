package com.example.speakerbalancer.range;

import androidx.annotation.NonNull;

public class Range {
    Ranges min, max;

    public Range(Ranges min, Ranges max) {
        this.min = min;
        this.max = max;
    }

    @NonNull
    public String toString() {
        return min + " - " + max;
    }

    public Ranges getMin() {
        return min;
    }

    public void setMin(Ranges min) {
        this.min = min;
    }

    public Ranges getMax() {
        return max;
    }

    public void setMax(Ranges max) {
        this.max = max;
    }
}
