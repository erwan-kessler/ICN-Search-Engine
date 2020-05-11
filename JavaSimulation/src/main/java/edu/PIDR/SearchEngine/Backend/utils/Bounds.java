package edu.PIDR.SearchEngine.Backend.utils;

public class Bounds {
    private final int min;
    private final int max;

    public Bounds(int min, int max) {
        this.max = max;
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "Bounds{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
