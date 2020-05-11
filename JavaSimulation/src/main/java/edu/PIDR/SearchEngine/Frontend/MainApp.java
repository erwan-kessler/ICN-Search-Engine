package edu.PIDR.SearchEngine.Frontend;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import edu.PIDR.SearchEngine.Frontend.Controllers.ErrorController;
import edu.PIDR.SearchEngine.Frontend.Controllers.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class MainApp extends Application {

    public final static Model instance = new Model();
    @Override
    public void start(Stage stage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(MainApp::showError);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml") );
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        root.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        MainController mainController=loader.getController();
        new JMetro(Style.LIGHT).setScene(scene);

        stage.setTitle("ICN Search Engine");
        stage.setScene(scene);
        stage.show();
    }

    public void init() {
        try {
            instance.initData();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void showError(Thread t, Throwable e) {
        if (Platform.isFxApplicationThread()) {
            showErrorDialog(e);
        } else {
            System.err.println("An unexpected error occurred in "+t);

        }
    }

    private static void showErrorDialog(Throwable e) {
        StringWriter errorMsg = new StringWriter();
        e.printStackTrace(new PrintWriter(errorMsg));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Error.fxml"));
        try {
            Parent root = loader.load();
            ((ErrorController)loader.getController()).setErrorText(errorMsg.toString());
            dialog.setScene(new Scene(root, 400, 400));
            dialog.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
