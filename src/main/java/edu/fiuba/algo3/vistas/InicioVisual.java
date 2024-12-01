package edu.fiuba.algo3.vistas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InicioVisual {
    private final Stage stage;

    public InicioVisual(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("UBAlatro");
        stage.show();
    }
}
