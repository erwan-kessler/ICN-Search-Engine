package edu.PIDR.SearchEngine.Backend.transfer;

public class Data {
    private final String body;
    private final String name;

    public Data(String body, String name) {
        this.body = body;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

}
