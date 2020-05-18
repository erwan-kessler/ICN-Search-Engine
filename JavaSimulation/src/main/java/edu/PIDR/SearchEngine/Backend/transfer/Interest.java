package edu.PIDR.SearchEngine.Backend.Transfer;

public class Interest {
    private final Interface interfaceReceived;
    private final String name;

    public Interest(String name,Interface interfaceReceived) {
        this.name = name;
        this.interfaceReceived=interfaceReceived;
    }

    public String getName() {
        return name;
    }

    public Interface getInterfaceReceived() {
        return interfaceReceived;
    }
}
