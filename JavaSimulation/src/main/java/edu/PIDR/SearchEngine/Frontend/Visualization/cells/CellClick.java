package edu.PIDR.SearchEngine.Frontend.Visualization.cells;

import edu.PIDR.SearchEngine.Backend.Components.Server;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import static edu.PIDR.SearchEngine.Frontend.MainApp.instance;

public class CellClick {

    public static void makeClickable(Region region, Server server) {
        region.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            instance.setCurrentServer(server);
        });
    }

}
