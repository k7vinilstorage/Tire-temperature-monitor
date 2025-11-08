package org.openjfx.interfacecertificadora;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;

import java.io.IOException;

import org.openjfx.interfacecertificadora.model.Storage;
import org.openjfx.interfacecertificadora.service.USBReader;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException { 
        scene = new Scene(loadFXML("interface"));
        stage.setMaximized(true);
        stage.setScene(scene);
        

        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setArmazen(){
        Storage tireArmazen = Storage.getInstance();

        USBReader reader = USBReader.getInstance();
        reader.addDataListener(value -> {
            tireArmazen.add(value);
        });
    }

    public static void main(String[] args) {
        
        setArmazen();
        launch(args);
    }

}