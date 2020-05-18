package edu.PIDR.SearchEngine.Backend.Transfer;

public class Packet {
    private final Data data;
    private int progress;

    public Packet(Data data) {
        this.data = data;
        this.progress = 0;
    }

    public Data getData() {
        return data;
    }

    public int getProgress() {
        return progress;
    }

    public void increaseProgress() {
        progress++;
    }
}
