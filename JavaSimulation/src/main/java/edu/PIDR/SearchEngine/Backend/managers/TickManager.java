package edu.PIDR.SearchEngine.Backend.managers;

import edu.PIDR.SearchEngine.Backend.components.Server;

import java.util.ArrayList;

public class TickManager {
    private final ArrayList<Server> registeredComponents=new ArrayList<>();
    private int tickPerMilliSecond;
    public TickManager(int tickPerMilliSecond){
        this.tickPerMilliSecond =tickPerMilliSecond;
    }

    public ArrayList<Server> getRegisteredComponents() {
        return registeredComponents;
    }

    public int getTickPerMilliSecond() {
        return tickPerMilliSecond;
    }

    public void setTickPerMilliSecond(int tickPerMilliSecond) {
        this.tickPerMilliSecond = tickPerMilliSecond;
    }

    public void addRegisteredComponents(Server component){
        registeredComponents.add(component);
    }
    public void runTick(){
        for (Server server : registeredComponents) {
            server.update();
        }
    }
    public void runMilliSecond(){
        for (int i = 0; i < tickPerMilliSecond; i++) {
           runTick();
        }

    }
}
