package edu.PIDR.SearchEngine.Frontend.Visualization.cells;

import edu.PIDR.SearchEngine.Backend.Components.Server;
import edu.PIDR.SearchEngine.Frontend.Visualization.graph.Graph;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RouterCell extends AbstractCell {
    Server server;

    public RouterCell(Server server) {
        this.server = server;
    }

    @Override
    public Region getGraphic(Graph graph) {
        final ImageView view = new ImageView(getClass().getResource("/Router.png").toString());
        view.setFitHeight(50);
        view.setFitWidth(70);

        final Pane pane = new Pane(view);
        pane.setPrefSize(70, 50);
        view.fitWidthProperty().bind(pane.prefWidthProperty());
        view.fitHeightProperty().bind(pane.prefHeightProperty());
        CellClick.makeClickable(pane,server);
        return pane;
    }



}
