package edu.PIDR.SearchEngine.Backend;

import edu.PIDR.SearchEngine.Backend.Components.Network;
import edu.PIDR.SearchEngine.Backend.Components.Router;
import edu.PIDR.SearchEngine.Backend.Transfer.Connection;
import edu.PIDR.SearchEngine.Backend.Transfer.Interface;
import edu.PIDR.SearchEngine.Backend.Utils.Bounds;
import edu.PIDR.SearchEngine.Backend.Utils.Utilities;

public class NetworkCreator {
    public static final boolean DEBUG=true;
    private final Utilities utilities = new Utilities();
    private final int nbServer;
    private final Bounds connPerServer;
    private final Bounds opsPerTick;
    private final Network network;
    private final Bounds rates;
    private final Bounds latencies;
    public NetworkCreator(int nbServer, Bounds connPerServer, Bounds opsPerTick, int tickPerMs,Bounds rates,Bounds latencies){
        this.nbServer=nbServer;
        this.connPerServer=connPerServer;
        this.opsPerTick=opsPerTick;
        this.rates=rates;
        this.latencies=latencies;
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
            Router router=new Router(utilities.getRandomNumberInRange(opsPerTick),i);
            if (DEBUG){
                System.out.println("Router with speed: "+router.getOpsPerTick()+" was added to the network");
            }
            network.addRouter(router);
        }
        for (Router router : network.getRouters()) {
            int connNumber=utilities.getRandomNumberInRange(connPerServer);
            for (int i = 0; i < connNumber; i++) {
                Router other_router=utilities.getRandomServer(network.getRouters(),router);
                Interface in=new Interface();
                Interface out=new Interface();
                Connection connection=new Connection(utilities.getRandomNumberInRange(rates),in,out,utilities.getRandomNumberInRange(latencies));
                router.addInterface(in);
                other_router.addInterface(out);
                router.addConnection(in,connection);
                other_router.addConnection(out,connection);
            }
        }
    }
}
