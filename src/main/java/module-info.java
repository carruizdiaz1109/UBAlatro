module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.jugadas;
    exports edu.fiuba.algo3.comodines;
    exports edu.fiuba.algo3.errores;
    exports edu.fiuba.algo3.tarots;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.media;
    exports edu.fiuba.algo3.controllers;

    opens edu.fiuba.algo3 to javafx.fxml, org.junit.jupiter, to .org.mockito;
    opens edu.fiuba.algo3.controllers to javafx.fxml;

}