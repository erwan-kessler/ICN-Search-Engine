package edu.PIDR.SearchEngine.Backend.Components;

import edu.PIDR.SearchEngine.Backend.Managers.NetworkManager;
import edu.PIDR.SearchEngine.Backend.Managers.TickManager;

import java.util.ArrayList;

public class Network {
    private final ArrayList<Router> routers = new ArrayList<>();
    private final ArrayList<SES> SES = new ArrayList<>();
    private TickManager tickManager;
    private NetworkManager networkManager;

    public Network(int tickPerMs) {
        tickManager = new TickManager(tickPerMs);
        networkManager = new NetworkManager();
    }

    public ArrayList<Router> getRouters() {
        return routers;
    }

    public ArrayList<SES> getSes() {
        return SES;
    }

    public void addRouter(Router router) {
        routers.add(router);
    }

    public void addSES(SES ses) {
        SES.add(ses);
    }
}
