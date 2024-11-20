package edu.fiuba.algo3;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        /*
        // Layout
        VBox layout = new VBox(10); // Espaciado de 10px
        layout.getChildren().addAll(button1, button2);
        */
        // Escena y ventana
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);

        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/icon.png")).toString());
        stage.getIcons().add(icon);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("perdiste");
        stage.setTitle("UBAlatro");
        stage.setScene(scene);
        stage.show();
    }


}
