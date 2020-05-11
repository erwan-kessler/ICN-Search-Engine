package edu.PIDR.SearchEngine.Backend.transfer;

import java.util.UUID;

public class Interface {
    private final String uniqueInterface;

    public Interface() {
        uniqueInterface = UUID.randomUUID().toString();
    }

    public String getUniqueInterface() {
        return uniqueInterface;
    }
}
