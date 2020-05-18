package edu.PIDR.SearchEngine.Frontend.Visualization;

import edu.PIDR.SearchEngine.Backend.Components.Network;
import edu.PIDR.SearchEngine.Backend.Components.Router;
import edu.PIDR.SearchEngine.Backend.Transfer.Connection;
import edu.PIDR.SearchEngine.Backend.Transfer.Interface;

import java.util.ArrayList;
import java.util.Optional;

public class Graph {
    private final ArrayList<Vertex> vertices=new ArrayList<>();
    private final ArrayList<Edge> connections=new ArrayList<>();

    public Graph(Network network) throws Exception {
        for (Router router : network.getRouters()) {
            Vertex vertex=new Vertex(router.getName(),new Position(0,0,0),router,router.getInterfaces().size(), Vertex.ServerType.ROUTER);
            vertices.add(vertex);
        }
        for(Router router:network.getRouters()){
            Vertex vertex= vertices.stream().filter(e -> e.getServer() == router).findFirst().orElseThrow(()-> new Exception("Missing server"));
            for (Interface anInterface : router.getInterfaces()) {
                Connection connection=router.getConnections().get(anInterface);
                Interface out=connection.getOut();
                Router other_router=network.getRouters().stream().filter(e->e.getInterfaces().contains(out)).findFirst().orElseThrow(()->new Exception("Missing interface"));
                Vertex other_vertex= vertices.stream().filter(e -> e.getServer() == other_router).findFirst().orElseThrow(()-> new Exception("Missing server"));
                Edge edge=new Edge(vertex,other_vertex);
                connections.add(edge);
            }
        }
    }

    public ArrayList<Edge> getConnections() {
        return connections;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
}
