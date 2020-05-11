package edu.PIDR.SearchEngine.Backend;

import edu.PIDR.SearchEngine.Backend.components.Network;
import edu.PIDR.SearchEngine.Backend.components.Router;
import edu.PIDR.SearchEngine.Backend.utils.Bounds;
import edu.PIDR.SearchEngine.Backend.utils.Utilities;

public class NetworkCreator {
    public static final boolean DEBUG=true;
    private final Utilities utilities = new Utilities();
    private final int nbServer;
    private final Bounds connPerServer;
    private final Bounds opsPerTick;
    private final Network network;
    public NetworkCreator(int nbServer, Bounds connPerServer, Bounds opsPerTick, int tickPerMs){
        this.nbServer=nbServer;
        this.connPerServer=connPerServer;
        this.opsPerTick=opsPerTick;
        network=new Network(tickPerMs);
    }

    public Bounds getConnPerServer() {
        return connPerServer;
    }

    public Bounds getOpsPerTick() {
        return opsPerTick;
    }

    public int getNbServer() {
        return nbServer;
    }
    public void createTopology(){
        for (int i = 0; i < nbServer; i++) {
            Router router=new Router(utilities.getRandomNumberInRange(opsPerTick));
            if (DEBUG){
                System.out.println("Router with speed: "+router.getOpsPerTick()+" was added to the network");
            }
            network.addRouter(router);
        }
        for (Router router : network.getRouters()) {

        }
    }
}
