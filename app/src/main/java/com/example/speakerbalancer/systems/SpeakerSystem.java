package com.example.speakerbalancer.systems;

import com.example.speakerbalancer.speakers.Speaker;

import java.util.Arrays;

public class SpeakerSystem {
    private final int amount;
    public final String name;
    protected Speaker[] speakers;
    private final LFE lfe;

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
        return Arrays.copyOf(speakers, getAmount());
    }

    public void setSpeakers(Speaker[] speakers) {
        this.speakers = speakers;
    }

    public String[] getNames() {
        String[] arr = new String[speakers.length];
        for (int i = 0; i < speakers.length; i++) arr[i] = speakers[i].getName();
        return arr;
    }

    public void setNames(String[] names) {
        for (int i = 0; i < speakers.length; i++) speakers[i].setName(names[i]);
    }

    public int[] getMinRanges() {
        int[] arr = new int[speakers.length];
        for (int i = 0; i < speakers.length; i++) arr[i] = speakers[i].getMinRange();
        return arr;
    }

    public void setMinRanges(int[] minRanges) {
        for (int i = 0; i < speakers.length; i++) speakers[i].setMinRange(minRanges[i]);
    }

    public int[] getMaxRanges() {
        int[] arr = new int[speakers.length];
        for (int i = 0; i < speakers.length; i++) arr[i] = speakers[i].getMaxRange();
        return arr;
    }

    public void setMaxRanges(int[] maxRanges) {
        for (int i = 0; i < speakers.length; i++) speakers[i].setMaxRange(maxRanges[i]);
    }

    public float[] getXBiases() {
        float[] arr = new float[speakers.length];
        for (int i = 0; i < speakers.length; i++) arr[i] = speakers[i].getXBias();
        return arr;
    }

    public void setXBiases(float[] xBiases) {
        for (int i = 0; i < speakers.length; i++) speakers[i].setXBias(xBiases[i]);
    }

    public float[] getYBiases() {
        float[] arr = new float[speakers.length];
        for (int i = 0; i < speakers.length; i++) arr[i] = speakers[i].getYBias();
        return arr;
    }

    public void setYBiases(float[] yBiases) {
        for (int i = 0; i < speakers.length; i++) speakers[i].setYBias(yBiases[i]);
    }

    public LFE getLfe() {
        return lfe;
    }
}
