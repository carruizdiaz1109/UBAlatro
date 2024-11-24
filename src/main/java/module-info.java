module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.jugadas;
    exports edu.fiuba.algo3.comodines;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.graphics;

    opens edu.fiuba.algo3 to javafx.fxml, org.junit.jupiter;
}