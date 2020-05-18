package edu.PIDR.SearchEngine.Frontend.Visualization;

import edu.PIDR.SearchEngine.Backend.Components.SES;
import edu.PIDR.SearchEngine.Backend.Components.Server;
import edu.PIDR.SearchEngine.Frontend.Visualization.cells.RouterCell;
import edu.PIDR.SearchEngine.Frontend.Visualization.cells.SESCell;
import edu.PIDR.SearchEngine.Frontend.Visualization.graph.ICell;

public class Vertex {
    private final String id;
    private final Position position;
    private final Server server;
    private final int nbConnection;
    private ICell cell=null;
    private final ServerType type;

    Vertex(String id, Position position, Server server, int nbConnection, ServerType type) {
        this.id = id;
        this.position = position;
        this.server = server;
        this.nbConnection = nbConnection;
        this.type=type;
    }

    public void setCell(ICell cell) {
        this.cell = cell;
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

    public ServerType getType() {
        return type;
    }

    public enum ServerType {
        SES(0),
        ROUTER(1),
        ROOT(2),
        OTHER(3);

        private final int id;

        ServerType(final int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }

    public ICell getCell() {
        return cell;
    }
}