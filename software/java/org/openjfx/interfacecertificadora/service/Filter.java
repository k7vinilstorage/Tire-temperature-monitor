package org.openjfx.interfacecertificadora.service;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;

public class Filter {
    private static TextFormatter<Integer> filterInt;

    public static TextFormatter<Integer> getFilterInt(){
        return new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if(text.matches("\\d*")){
                return change;
            }
            return null;
        });
    }
}
