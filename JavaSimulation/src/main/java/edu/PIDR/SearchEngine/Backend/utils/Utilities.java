package edu.PIDR.SearchEngine.Backend.utils;

import java.util.Random;

public class Utilities {

    public Utilities() {

    }

    public int getRandomNumberInRange(Bounds bounds) {
        int min = bounds.getMin();
        int max = bounds.getMax();
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
