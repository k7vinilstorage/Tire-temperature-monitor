module org.openjfx.interfacecertificadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;
    requires com.fazecast.jSerialComm;

    opens org.openjfx.interfacecertificadora to javafx.fxml;
    exports org.openjfx.interfacecertificadora;
    
    opens org.openjfx.interfacecertificadora.controller to javafx.fxml;
    exports org.openjfx.interfacecertificadora.controller;
    
    opens org.openjfx.interfacecertificadora.model to javafx.fxml;
    exports org.openjfx.interfacecertificadora.model;
    
    opens org.openjfx.interfacecertificadora.service to javafx.fxml;
    exports org.openjfx.interfacecertificadora.service;

    opens org.openjfx.interfacecertificadora.view to javafx.fxml;
    exports org.openjfx.interfacecertificadora.view;
    
}
