package edu.PIDR.SearchEngine.Frontend;

import edu.PIDR.SearchEngine.Backend.Components.Network;
import edu.PIDR.SearchEngine.Backend.Components.Server;
import edu.PIDR.SearchEngine.Backend.NetworkCreator;
import edu.PIDR.SearchEngine.Backend.Utils.Bounds;
import edu.PIDR.SearchEngine.Frontend.Visualization.Graph;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Model {
    private Network network;
    private Graph graph;
    private final SimpleObjectProperty<Server> currentServer=new SimpleObjectProperty<>(null);
    public void initData() throws Exception {
        NetworkCreator networkCreator=new NetworkCreator(10,new Bounds(1,1),new Bounds(1,10),100,new Bounds(1,100),new Bounds(1,100));
        networkCreator.createTopology();
        network=networkCreator.getNetwork();
        graph=new Graph(network);

    }

    public Network getNetwork() {
        return network;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setCurrentServer(Server currentServer) {
        this.currentServer.setValue(currentServer);
    }

    public ObjectProperty<Server> getCurrentServer() {
        return currentServer;
    }
}