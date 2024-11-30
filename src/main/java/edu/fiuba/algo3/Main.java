package edu.fiuba.algo3;

import java.util.Objects;

import edu.fiuba.algo3.controllers.InicioController;
import edu.fiuba.algo3.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import utilities.Paths;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Inicio.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de Inicio.fxml
        InicioController inicioController = loader.getController();
        inicioController.setStage(stage); // Pasar la referencia del Stage al controlador

        Scene scene = new Scene(root);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);
        stage.setTitle("UBAlatro");

        stage.setScene(scene);
        stage.show();
    }

}