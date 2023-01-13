package com.example.speakerbalancer.systems;

import com.example.speakerbalancer.speakers.Speaker;

import java.util.Arrays;

public class SpeakerSystem {
    public int amount;
    public String name;
    public Speaker[] speakers;
    public LFE lfe;

    public SpeakerSystem(int amount, String name, boolean enableLFE, boolean checkLFE) {
        this.amount = amount;
        this.name = name;
        this.lfe = new LFE(enableLFE, checkLFE);
        this.speakers = new Speaker[amount + (lfe.isEnabled() ? 1 : 0)];
    }

    protected String notation() {
        return amount + "." + (lfe.isChecked() ? 1 : 0);
    }

    public int findIndex() {
        SystemDirectory[] values = SystemDirectory.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].speakerSystem.name.equals(this.name)) return i;
        }
        return 0;
    }

    public int getAmount() {
        return amount + (lfe.isChecked() ? 1 : 0);
    }

    public String getName() {
        return name + " (" + this.notation() + ")";
    }

    public Speaker[] getSpeakers() {
        Speaker[] arr = speakers;
        if (!lfe.isChecked()) arr = Arrays.copyOf(arr, arr.length - 1);
        return arr;
    }

    public double[] getxBiases() {
        double[] arr = new double[speakers.length];
        for (int i = 0; i < speakers.length; i++) {
            arr[i] = (int) speakers[i].channel.getxBias();
        }
        return arr;
    }

    public double[] getyBiases() {
        double[] arr = new double[speakers.length];
        for (int i = 0; i < speakers.length; i++) {
            arr[i] = (int) speakers[i].channel.getyBias();
        }
        return arr;
    }
}
