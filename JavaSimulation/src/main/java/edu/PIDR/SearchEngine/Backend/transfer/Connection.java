package edu.PIDR.SearchEngine.Backend.Transfer;

import java.util.ArrayList;
// this depic a single way pipe
public class Connection {
    private final int rate;
    private final Interface in;
    private final Interface out;
    private int saturation = 0;
    private final int latency;
    private final ArrayList<Packet> packetsInTransit =new ArrayList<>();
    private final ArrayList<Packet> packetsOut =new ArrayList<>();

    public Connection(int rate, Interface in, Interface out, int latency) {
        this.rate = rate;
        this.in = in;
        this.out = out;
        this.latency = latency;
    }

    public boolean addPacket(Packet packet){
        if (isSaturated()){
            return false;
        }
        packetsInTransit.add(packet);
        increaseSaturation();
        return true;
    }

    public void update(){
        for (Packet packet : packetsInTransit) {
            packet.increaseProgress();
            if (packet.getProgress()==latency){
                packetsOut.add(packet);
            }
        }
        for (Packet packet:packetsOut){
            packetsInTransit.remove(packet);
            decreaseSaturation();
        }
    }
    public boolean isTherePacketsOut(){
        return !getPacketsOut().isEmpty();
    }

    public ArrayList<Packet> getPacketsInTransit() {
        return packetsInTransit;
    }

    public ArrayList<Packet> getPacketsOut() {
        return packetsOut;
    }

    public int getLatency() {
        return latency;
    }

    public int getSaturation() {
        return saturation;
    }

    public boolean isSaturated() {
        return saturation >= rate;
    }

    public void increaseSaturation() {
        saturation++;
    }

    public void decreaseSaturation() {
        saturation--;
    }

    public int getRate() {
        return rate;
    }

    public Interface getIn() {
        return in;
    }

    public Interface getOut() {
        return out;
    }
}
