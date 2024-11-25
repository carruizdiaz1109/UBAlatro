package edu.fiuba.algo3;

import java.util.Objects;

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
        // Carga el archivo FXML de la interfaz principal
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main.fxml")));

        // Configuración de la escena principal
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Configuración de la ventana
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);
        stage.setTitle("UBAlatro");
        //stage.setFullScreen(true);
        //stage.setResizable(false);

        stage.show();

    /*

        //Si agrego estas 4 lineas ya no me muestra las cartas pero si añade funcionalidad al boton jugar, una ves iniciado jugar deberia repartir la mano, crear el mazo
        // y la tienda, el controlador se deberia encargar de eso.
        HBox load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.MAIN)));
        Scene sceneJugar = new Scene(load);
        stage.setScene(sceneJugar);
        stage.show();*/
    }
}