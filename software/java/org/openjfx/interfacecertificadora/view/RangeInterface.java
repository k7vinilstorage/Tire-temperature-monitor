package org.openjfx.interfacecertificadora.view;

import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.Optional;
import java.util.function.UnaryOperator;

import org.openjfx.interfacecertificadora.model.TimeRange;
import org.openjfx.interfacecertificadora.service.Filter;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;

public class RangeInterface {
    private static TextField startField;
    private static TextField endField;

    public static TimeRange getRange() throws IOException{
        int start;
        int end;

        TimeRange timeRange;

        Dialog<ButtonType> dialog = setDialog();

        while(true){
            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){
                try{
                    start = Integer.valueOf(startField.getText());
                }catch (NumberFormatException e){
                    start = 0;
                }
                
                try{
                    end = Integer.valueOf(endField.getText());
                }catch (NumberFormatException e){
                    end = -1;
                }

                if (start < 0 || (end < 0 && end != -1)){
                    Notify.notify("error", "Valor invalido", "Valores não podem ser naegativo", null);
                    continue;
                }else if (end == 0 ){
                    Notify.notify("error", "Valor invalido", "O fim não pode ser 0   ", null);
                    continue;
                }else if(start > end && end != -1){
                    Notify.notify("error", "Valor invalido", "Inicio não pode ser maior que o fim", null);
                    continue;
                }

                timeRange = new TimeRange(start, end);
                break;
            }else{
                throw new IOException("Cacelado");
            }
        }

        return timeRange;
    }

    public static Dialog<ButtonType> setDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setTitle("Selecione o intervalo");
        dialog.setHeaderText("Selecione o intervalo em segundos");

        startField = new TextField();
        startField.setTextFormatter(Filter.getFilterInt());

        endField = new TextField();
        endField.setTextFormatter(Filter.getFilterInt());

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col1.setMinWidth(Region.USE_PREF_SIZE);
        col2.setHgrow(Priority.ALWAYS); 

        grid.getColumnConstraints().addAll(col1, col2);

        grid.add(new Label("Inicio: "),0,0);
        grid.add(startField, 1,0);
        startField.setMaxWidth(Double.MAX_VALUE);

        grid.add(new Label("Fim: "),0,1);
        grid.add(endField, 1,1);
        endField.setMaxWidth(Double.MAX_VALUE);

        dialog.getDialogPane().setContent(grid);

        dialog.getDialogPane().getStylesheets().add(
            RangeInterface.class.getResource("/org/openjfx/interfacecertificadora/style/range.css").toExternalForm()
        );

        return dialog;
    }
}
