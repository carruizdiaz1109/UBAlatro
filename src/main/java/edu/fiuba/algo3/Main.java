package edu.fiuba.algo3;

import java.util.Objects;

import edu.fiuba.algo3.controllers.BalatroController;
import edu.fiuba.algo3.controllers.InicioController;
import edu.fiuba.algo3.controllers.RondaController;
import edu.fiuba.algo3.controllers.TiendaController;
import edu.fiuba.algo3.modelo.entidades.Balatro;
import edu.fiuba.algo3.vistas.InicioVisual;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BalatroController balatroController = new BalatroController();
        FXMLLoader loaderInicio = new FXMLLoader(getClass().getResource("/inicio.fxml"));
        Parent rootInicio = loaderInicio.load();

        InicioController inicioController = loaderInicio.getController();
        inicioController.setBalatroController(balatroController);
        inicioController.setStage(stage);

        Scene inicioScene = new Scene(rootInicio);
        stage.setScene(inicioScene);
        stage.setTitle("UBAlatro - Inicio");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);
        stage.show();
    }
}