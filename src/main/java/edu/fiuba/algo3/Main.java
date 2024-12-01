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

        // Pasar los controladores e información al InicioController
        inicioController.setBalatroController(balatroController);
        inicioController.setStage(stage);  // Pasamos el Stage al controller de Inicio

        Scene inicioScene = new Scene(rootInicio);
        stage.setScene(inicioScene);
        stage.setTitle("UBAlatro - Inicio");
        stage.show();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
//        Parent root = loader.load();
//        InicioController inicioController = loader.getController();
//
//        Balatro balatro = new Balatro("Nombre del Jugador");
//
//        balatroController.setBalatro(balatro);
//        balatroController.setStage(stage);
//        balatroController.avanzarRonda();
//        FXMLLoader loaderRonda = new FXMLLoader(getClass().getResource("/ronda.fxml"));
//        loader.setController(rondaController);
//        Parent rootRonda = loaderRonda.load();
//        Scene sceneRonda = new Scene(rootRonda);
//        stage.setScene(sceneRonda);
//        stage.setTitle("UBAlatro - Tienda");

    }

}