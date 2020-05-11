package edu.PIDR.SearchEngine.Backend.components;

import edu.PIDR.SearchEngine.Backend.transfer.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Router extends Server{
    public Router(int opsPerTick){
        super(opsPerTick);
    }

    @Override
    public void update() {
        for (int i = 0; i < getOpsPerTick(); i++) {
            this.processOneRequest();
        }
        for (Interface face:getInterfaces()){
            if (getConnections().containsKey(face)){
                Connection connection=getConnections().get(face);
                connection.update();
                // if there is packets out of the connections
                if (connection.isTherePacketsOut()){
                    for (Packet packet : connection.getPacketsOut()) {
                        // transmit the packets around
                        if (packet.getData().getBody()==null){
                            // if the data is null then the data was not retrieve and will be processed on next tick
                            addInterest(new Interest(packet.getData().getName(),face));
                            if (DEBUG){
                                System.out.println("A new interest was created since the data needs to be propagated: "+packet.getData().getName()+" on face: "+face);
                            }
                        }else{
                            // else then the data just need to be retransmit to the previous interface
                            if (getPIT().containsKey(packet.getData().getName())){
                                ArrayList<Interface> interfaces=getPIT().get(packet.getData().getName());
                                for (Interface anInterface : interfaces) {
                                    // transmit to each PIT interface the packet
                                    getConnections().get(anInterface).addPacket(packet);
                                    System.out.println("A new packet was sent on: "+anInterface+" it contains: "+packet.getData().getName());
                                }
                            }else{
                                if (DEBUG){
                                    System.out.println("ERROR, the PIT dont have the entry to sent back the data");
                                }
                            }
                        }
                    }
                    // remove the packets since they were used
                    connection.getPacketsOut().clear();
                }
            }
        }
    }

    private void processOneRequest(){
        Iterator<Interest> iterator= getInterests().iterator();
        if (iterator.hasNext()){
            Interest interest=iterator.next();
            if (getCS().containsKey(interest.getName())){
                // using cache
                if (DEBUG)System.out.println("Interest: "+interest.getName()+" have been found in CS");
                Packet packet=new Packet(getCS().get(interest.getName()));
                getConnections().get(interest.getInterfaceReceived()).addPacket(packet);
            }else {
                // adding to PIT the interest (will be removed once it is resolved)
                if (!getPIT().containsKey(interest.getName())) {
                    if (DEBUG) System.out.println("Interest: " + interest.getName() + " new entry was added to PIT");
                    getPIT().put(interest.getName(), new ArrayList<>());
                }
                if (DEBUG)System.out.println("Interest: "+interest.getName()+" interface was added to already existing entry in PIT");
                getPIT().get(interest.getName()).add(interest.getInterfaceReceived());
                // getting the proper interfaces from the FIB and adding the packets to forward
                ArrayList<Interface> interfaces=super.computeClosestMatch(interest.getName());
                Packet packet=new Packet(new Data(null,interest.getName()));
                for (Interface anInterface : interfaces) {
                    getConnections().get(anInterface).addPacket(packet);
                }
            }
        }
    }
}
