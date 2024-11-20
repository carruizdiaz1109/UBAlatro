package edu.fiuba.algo3;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear botones
        Button button1 = new Button("Botón 1");
        Button button2 = new Button("Botón 2");

        // Evento en botón
        button1.setOnAction(e -> System.out.println("¡Botón 1 presionado!"));
        button2.setOnAction(e -> System.out.println("¡Botón 2 presionado!"));

        // Layout
        VBox layout = new VBox(10); // Espaciado de 10px
        layout.getChildren().addAll(button1, button2);

        // Escena y ventana
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Aplicación JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
