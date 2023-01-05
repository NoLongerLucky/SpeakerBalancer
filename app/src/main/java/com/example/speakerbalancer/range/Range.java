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
}
