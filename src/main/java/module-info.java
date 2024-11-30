module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.modelo.entidades.jugadas;
    exports edu.fiuba.algo3.modelo.entidades.comodines;
    exports edu.fiuba.algo3.modelo.entidades.tarots;
    exports edu.fiuba.algo3.modelo.excepciones;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.media;
    exports edu.fiuba.algo3.controllers;

    opens edu.fiuba.algo3 to javafx.fxml, org.junit.jupiter, to .org.mockito;
    opens edu.fiuba.algo3.controllers to javafx.fxml;
    exports edu.fiuba.algo3.modelo.entidades;
    opens edu.fiuba.algo3.modelo.entidades to javafx.fxml, org.junit.jupiter, to.org.mockito;
    opens edu.fiuba.algo3.modelo.excepciones to javafx.fxml, org.junit.jupiter, to.org.mockito;
    exports edu.fiuba.algo3.modelo.interfaces;
    opens edu.fiuba.algo3.modelo.interfaces to javafx.fxml, org.junit.jupiter, to.org.mockito;

}