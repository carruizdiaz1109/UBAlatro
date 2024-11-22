package edu.fiuba.algo3;

import java.util.Objects;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main.fxml")));

        Image cartaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/cartas/14P.png")));
        javafx.scene.image.ImageView cartaView = new javafx.scene.image.ImageView(cartaImage);

        cartaView.setFitWidth(150);
        cartaView.setFitHeight(225);
        cartaView.setPreserveRatio(true);

        StackPane cartaPane = new StackPane(cartaView);
        cartaPane.setPickOnBounds(false);
        cartaPane.setStyle("-fx-background-color: transparent;");
        cartaPane.setMouseTransparent(false);
        cartaPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        StackPane.setAlignment(cartaView, javafx.geometry.Pos.CENTER);
        StackPane rootPane = new StackPane(root, cartaPane);

        Scene scene = new Scene(rootPane);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("UBAlatro");

        stage.setScene(scene);
        stage.show();
    }
}
