package edu.PIDR.SearchEngine.Frontend.Visualization;

import edu.PIDR.SearchEngine.Backend.Components.Server;

public class Vertex {
    private final String id;
    private final Position position;
    private final Server server;
    private final int nbConnection;
    Vertex(String id, Position position, Server server, int nbConnection) {
        this.id = id;
        this.position=position;
        this.server=server;
        this.nbConnection=nbConnection;
    }

    public int getNbConnection() {
        return nbConnection;
    }

    public Server getServer() {
        return server;
    }

    public Position getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }
}