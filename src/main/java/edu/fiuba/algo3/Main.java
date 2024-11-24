package edu.fiuba.algo3;

import java.util.Objects;

import edu.fiuba.algo3.vistas.CartaVisual;
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

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main.fxml")));
        // todo esto esta medio de prueba
        CartaVisual carta1 = new CartaVisual("/imagenes/cartas/14P.png", 150, 225);
        CartaVisual carta2 = new CartaVisual("/imagenes/cartas/7C.png", 150, 225);
        CartaVisual carta3 = new CartaVisual("/imagenes/cartas/13D.png", 150, 225);

        carta1.setLayoutX(400);
        carta1.setLayoutY(300);

        carta2.setLayoutX(600);
        carta2.setLayoutY(300);

        carta3.setLayoutX(800);
        carta3.setLayoutY(300);

        javafx.scene.layout.Pane cartaPane = new javafx.scene.layout.Pane(carta1, carta2, carta3);
        cartaPane.setStyle("-fx-background-color: transparent;");

        StackPane rootPane = new StackPane(root, cartaPane);

        Scene scene = new Scene(rootPane);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);
        //stage.setResizable(false);
        stage.setTitle("UBAlatro");

        stage.setScene(scene);
        stage.show();

        //Si agrego estas 4 lineas ya no me muestra las cartas pero si añade funcionalidad al boton jugar, una ves iniciado jugar deberia repartir la mano, crear el mazo
        // y la tienda, el controlador se deberia encargar de eso.
        /*HBox load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.MAIN)));
        Scene sceneJugar = new Scene(load);
        stage.setScene(sceneJugar);
        stage.show();*/
    }
}