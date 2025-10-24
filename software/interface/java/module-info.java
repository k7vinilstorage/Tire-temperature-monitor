module org.openjfx.interfacecertificadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    opens org.openjfx.interfacecertificadora to javafx.fxml;
    exports org.openjfx.interfacecertificadora;
}
