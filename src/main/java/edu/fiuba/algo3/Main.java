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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main.fxml")));

        Scene scene = new Scene(root);
        Image icon = new Image("/icon.png");
        stage.getIcons().add(icon);
        stage.setFullScreen(true);
        stage.setTitle("UBAlatro");

        stage.setScene(scene);
        stage.show();
    }
}
