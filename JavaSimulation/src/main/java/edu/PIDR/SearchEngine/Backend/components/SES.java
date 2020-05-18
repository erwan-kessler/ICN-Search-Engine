package edu.PIDR.SearchEngine.Backend.Components;

import edu.PIDR.SearchEngine.Backend.Transfer.Connection;

import java.util.ArrayList;

public class SES extends Server{
    private final ArrayList<Connection> logicalConnections=new ArrayList<>();
    public SES(int opsPerTick,int id){
        super(opsPerTick,"SES "+id);
    }

    @Override
    public void update() {
        for (int i = 0; i < getOpsPerTick(); i++) {
            this.processOneRequest();
        }
    }

    public void processOneRequest(){

    }

    public ArrayList<Connection> getLogicalConnections() {
        return logicalConnections;
    }

    public void addLogicalConnection(Connection connection) {
       this.logicalConnections.add(connection);
    }
}
