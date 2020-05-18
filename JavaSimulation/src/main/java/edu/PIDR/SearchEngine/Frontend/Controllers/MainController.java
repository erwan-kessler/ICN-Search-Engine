package edu.PIDR.SearchEngine.Frontend.Controllers;

import edu.PIDR.SearchEngine.Backend.Components.Server;
import edu.PIDR.SearchEngine.Frontend.Visualization.Edge;
import edu.PIDR.SearchEngine.Frontend.Visualization.Vertex;
import edu.PIDR.SearchEngine.Frontend.Visualization.cells.RouterCell;
import edu.PIDR.SearchEngine.Frontend.Visualization.cells.SESCell;
import edu.PIDR.SearchEngine.Frontend.Visualization.graph.Graph;
import edu.PIDR.SearchEngine.Frontend.Visualization.graph.ICell;
import edu.PIDR.SearchEngine.Frontend.Visualization.graph.Model;
import edu.PIDR.SearchEngine.Frontend.Visualization.layout.AbegoTreeLayout;
import edu.PIDR.SearchEngine.Frontend.Visualization.layout.RandomLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.abego.treelayout.Configuration;

import java.net.URL;
import java.util.ResourceBundle;

import static edu.PIDR.SearchEngine.Frontend.MainApp.instance;

public class MainController implements Initializable {
    @FXML
    private AnchorPane information;
    @FXML
    private AnchorPane parameters;
    @FXML
    private AnchorPane graph;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize the main component
        Graph tree = new Graph();
        addGraphComponents(tree);
        graph.getChildren().add(tree.getCanvas());
        instance.getCurrentServer().addListener((observable, oldValue, newValue) -> {
            addInformations(newValue);
        });

    }

    public void addGraphComponents(Graph graph) {
        final Model model = graph.getModel();
        graph.beginUpdate();
        edu.PIDR.SearchEngine.Frontend.Visualization.Graph graph1 = instance.getGraph();
        for (Edge connection : graph1.getConnections()) {
            Vertex vertex1=connection.getVertices()[0];
            Vertex vertex2=connection.getVertices()[1];
            if (vertex1.getCell() == null) {
               ICell cell1=vertex1.getType() == Vertex.ServerType.SES ? new SESCell(vertex1.getServer()) : new RouterCell(vertex1.getServer());
                model.addCell(cell1);
                vertex1.setCell(cell1);
            }
            if (vertex2.getCell() == null) {
                ICell cell2 = vertex2.getType() == Vertex.ServerType.SES ? new SESCell(vertex2.getServer()) : new RouterCell(vertex2.getServer());
                model.addCell(cell2);
                vertex2.setCell(cell2);
            }
            model.addEdge(vertex1.getCell(),vertex2.getCell());
        }
        graph.endUpdate();
        graph.layout(new RandomLayout());
    }

    public void addInformations(Server server){
        Pane pane=new Pane();
        pane.getChildren().add(new Text("eee"));
        information.getChildren().add(pane);
        information.setVisible(false);

    }
}