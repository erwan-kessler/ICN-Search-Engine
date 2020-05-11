package edu.PIDR.SearchEngine.Backend.components;

import edu.PIDR.SearchEngine.Backend.transfer.Connection;
import edu.PIDR.SearchEngine.Backend.transfer.Data;
import edu.PIDR.SearchEngine.Backend.transfer.Interest;
import edu.PIDR.SearchEngine.Backend.transfer.Interface;

import java.util.*;

public abstract class Server {
    public static boolean DEBUG = true;
    public int limitPrefixMatch = 5;
    private int opsPerTick;
    private final HashMap<Interface, Connection> connections = new HashMap<>();
    private final HashMap<String, ArrayList<Interface>> PIT = new HashMap<>();
    private final HashMap<String, Interface> FIB = new HashMap<>();
    private final HashMap<String, Data> CS = new HashMap<>();
    private final ArrayList<Interest> interests = new ArrayList<>();
    private final ArrayList<Interface> interfaces = new ArrayList<>();

    public Server(int opsPerTick) {
        this.opsPerTick = opsPerTick;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public ArrayList<Interface> getInterfaces() {
        return interfaces;
    }

    public void addInterface(Interface i) {
        interfaces.add(i);
    }

    public void addInterest(Interest interest) {
        interests.add(interest);
    }

    public int getOpsPerTick() {
        return opsPerTick;
    }

    public void setOpsPerTick(int opsPerTick) {
        this.opsPerTick = opsPerTick;
    }

    public HashMap<Interface, Connection> getConnections() {
        return connections;
    }

    public void addConnection(Interface face, Connection connection) {
        if (this.connections.containsKey(face)) {
            if (DEBUG) System.out.println("Cant override a connection");
        }
        this.connections.put(face, connection);
    }

    public HashMap<String, Interface> getFIB() {
        return FIB;
    }

    public HashMap<String, Data> getCS() {
        return CS;
    }

    public HashMap<String, ArrayList<Interface>> getPIT() {
        return PIT;
    }

    public abstract void update();


    public ArrayList<Interface> computeClosestMatch(String name) {
        Map<Integer, String> sortedEntries = new TreeMap<>(Collections.reverseOrder());
        for (String entry : FIB.keySet()) {
            if (name.startsWith(entry)) {
                sortedEntries.put(entry.length(), entry);
            }
        }
        if (DEBUG) {
            System.out.println("Current sorted entries contains: " + sortedEntries);
        }
        if (sortedEntries.isEmpty()) {
            // we dont have anything in the FIB that can allow forwarding so we must flood
            if (DEBUG){
                System.out.println("Warning Flooding "+interfaces);
            }
            return interfaces;
        } else {
            ArrayList<Interface> finalInterfaces=new ArrayList<>();
            Iterator<String> iterator=sortedEntries.values().iterator();
            for (int i = 0; i < limitPrefixMatch; i++) {
                if (iterator.hasNext()){
                   finalInterfaces.add(FIB.get(iterator.next()));
                }
            }
            if (DEBUG){
                System.out.println("Giving those interfaces: "+finalInterfaces);
            }
            return finalInterfaces;
        }
    }
}
