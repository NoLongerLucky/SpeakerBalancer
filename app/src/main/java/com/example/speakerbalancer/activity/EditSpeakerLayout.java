package com.example.speakerbalancer.activity;

import android.os.Bundle;

import com.example.speakerbalancer.R;

public class EditSpeakerLayout extends EditConfiguration {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setLayout() {
        setContentView(R.layout.activity_edit_speaker_layout);
    }
}