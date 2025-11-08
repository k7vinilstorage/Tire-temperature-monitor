package org.openjfx.interfacecertificadora.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

public class Notify {
    private static Alert alert;
    private static boolean buttonOk = false;

    public Notify() {
    }

    public static void notify(String type, String title, String header, String text){
        switch (type) {
            case "information":
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;

            case "warning":
                alert = new Alert(Alert.AlertType.WARNING);
                break;

            case "error":
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            
            case "confirmation":
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;

        
            default:
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
        }
        alert.setTitle(title);
        alert.setHeaderText(header  );
        alert.setContentText(text);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
            Notify.class.getResource("/org/openjfx/interfacecertificadora/style/alert.css").toExternalForm()
        );
        dialogPane.getStyleClass().add("my-alert");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK){
                buttonOk = true;
            }else{
                buttonOk = false;
            }
        });
    }

    public static boolean getButtonResponse(){
        return buttonOk;
    }
}
