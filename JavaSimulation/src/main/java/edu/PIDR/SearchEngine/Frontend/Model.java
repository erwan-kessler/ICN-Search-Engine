package edu.PIDR.SearchEngine.Frontend;

import edu.PIDR.SearchEngine.Backend.Components.Network;
import edu.PIDR.SearchEngine.Backend.NetworkCreator;
import edu.PIDR.SearchEngine.Backend.Utils.Bounds;
import edu.PIDR.SearchEngine.Frontend.Visualization.Graph;

public class Model {
    private Network network;
    private Graph graph;
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
}