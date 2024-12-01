package edu.fiuba.algo3;

import java.util.Objects;

import edu.fiuba.algo3.controllers.InicioController;
import edu.fiuba.algo3.controllers.RondaController;
import edu.fiuba.algo3.controllers.TiendaController;
import edu.fiuba.algo3.modelo.entidades.Balatro;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
        Parent root = loader.load();
        InicioController inicioController = loader.getController();
        inicioController.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("UBAlatro");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);
        stage.show();

        String nombre = inicioController.getNombreJugador();
        Balatro balatro = new Balatro(nombre);
        RondaController rondaController = new RondaController(balatro);

        FXMLLoader loaderRonda = new FXMLLoader(getClass().getResource("/ronda.fxml"));
        loader.setController(rondaController);
        Parent rootRonda = loaderRonda.load();
        Scene sceneRonda = new Scene(rootRonda);
        stage.setScene(sceneRonda);
        stage.setTitle("UBAlatro - Tienda");

    }

}