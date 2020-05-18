package edu.PIDR.SearchEngine.Frontend.Visualization.cells;

import edu.PIDR.SearchEngine.Backend.Components.Server;
import edu.PIDR.SearchEngine.Frontend.Visualization.graph.Graph;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class SESCell extends AbstractCell {

    Server server;

    public SESCell(Server server) {
        this.server = server;
    }

    @Override
    public Region getGraphic(Graph graph) {
        final ImageView view = new ImageView(getClass().getResource("/SES.png").toString());
        view.setFitHeight(50);
        view.setFitWidth(60);

        final Pane pane = new Pane(view);
        pane.setPrefSize(60, 50);
        view.fitWidthProperty().bind(pane.prefWidthProperty());
        view.fitHeightProperty().bind(pane.prefHeightProperty());

        return pane;
    }


}
