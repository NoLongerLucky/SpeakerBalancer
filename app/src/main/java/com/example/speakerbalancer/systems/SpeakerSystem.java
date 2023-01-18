package com.example.speakerbalancer.systems;

import com.example.speakerbalancer.speakers.Speaker;

public class SpeakerSystem {
    private int amount;
    public final String name;
    protected Speaker[] speakers;
    private LFE lfe;

    public SpeakerSystem(int amount, String name, boolean enableLFE, boolean checkLFE) {
        this.amount = amount;
        this.name = name;
        this.lfe = new LFE(enableLFE, checkLFE);
        this.speakers = new Speaker[amount + (lfe.isEnabled() ? 1 : 0)];
    }

    protected String notation() {
        return amount + "." + (lfe.isChecked() ? 1 : 0);
    }

    public int directoryIndex() {
        SystemDirectory[] values = SystemDirectory.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getSpeakerSystem().name.equals(this.name)) return i;
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
        return speakers;
    }

    public void setSpeakers(Speaker[] speakers) {
        this.speakers = speakers;
    }

    public double[] getxBiases() {
        double[] arr = new double[speakers.length];
        for (int i = 0; i < speakers.length; i++) {
            arr[i] = (int) speakers[i].getChannel().getxBias();
        }
        return arr;
    }

    public double[] getyBiases() {
        double[] arr = new double[speakers.length];
        for (int i = 0; i < speakers.length; i++) {
            arr[i] = (int) speakers[i].getChannel().getyBias();
        }
        return arr;
    }

    public LFE getLfe() {
        return lfe;
    }
}
