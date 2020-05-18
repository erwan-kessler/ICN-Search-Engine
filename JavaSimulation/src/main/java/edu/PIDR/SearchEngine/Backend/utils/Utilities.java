package edu.PIDR.SearchEngine.Backend.Utils;

import edu.PIDR.SearchEngine.Backend.Components.Router;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {
    long seed= 1234L;
    Random r = new Random(seed);
    public Utilities() {

    }

    public int getRandomNumberInRange(Bounds bounds) {
        int min = bounds.getMin();
        int max = bounds.getMax();
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }


        return r.nextInt((max - min) + 1) + min;
    }

    public Router getRandomServer(ArrayList<Router> routers,Router routerToExclude){
        Router randomRouter;
        while ((randomRouter=routers.get(r.nextInt(routers.size())))==routerToExclude){
            ;
        }
        return randomRouter;
    }
}
