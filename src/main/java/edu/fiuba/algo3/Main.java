package edu.fiuba.algo3;

import java.util.Objects;

import edu.fiuba.algo3.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = loader.load();

        // Obtener el controlador desde el FXML cargado
        MainController controlador = loader.getController();

        // Actualiza la mano para mostrar las cartas en el HBox
        controlador.clickJugar();

        Scene scene = new Scene(root);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);
        //stage.setResizable(false);
        stage.setTitle("UBAlatro");

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();

        //Si agrego estas 4 lineas ya no me muestra las cartas pero si añade funcionalidad al boton jugar, una ves iniciado jugar deberia repartir la mano, crear el mazo
        // y la tienda, el controlador se deberia encargar de eso.
        HBox load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.MAIN)));
        Scene sceneJugar = new Scene(load);
        stage.setScene(sceneJugar);
        stage.show();
    }
}