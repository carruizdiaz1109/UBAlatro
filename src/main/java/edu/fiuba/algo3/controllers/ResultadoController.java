package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Balatro;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;


public class ResultadoController {

    @FXML
    private Label lblResultado;

    @FXML
    private Button btnVolverAJugar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnRondaSiguiente;

    @FXML
    private VBox root;

    @FXML
    private VBox resultadoBox;
    private BalatroController balatroController;

    @FXML
    public void initialize() {
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        btnVolverAJugar.setOnAction(event -> volverAJugar());
        btnSalir.setOnAction(event -> salir());
        btnRondaSiguiente.setOnAction(event -> siguienteRonda());
        aplicarTransicion();
    }

    public void setBalatroController(BalatroController balatroController) {
        this.balatroController = balatroController;
    }

    public void mostrarMensaje(String mensaje, Balatro.EstadoJuego estadoJuego) {
        lblResultado.setText(mensaje);  // Actualiza el texto con el mensaje

        // Controla el estado del cuadro de resultado según el estado del juego
        if (estadoJuego == Balatro.EstadoJuego.EMPEZADO) {
            // Recuadro verde para la ronda intermedia
            resultadoBox.setStyle("-fx-background-color: white; -fx-border-color: #6D9F4B; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 20; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0.5, 0, 0);");

            // Mostrar botón de siguiente ronda, ocultar el de volver a jugar
            btnRondaSiguiente.setVisible(true);
            btnVolverAJugar.setVisible(false);
        } else if (estadoJuego == Balatro.EstadoJuego.GANADO) {
            // Recuadro verde para la victoria final
            resultadoBox.setStyle("-fx-background-color: green; -fx-border-color: #4CAF50; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 20; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0.5, 0, 0);");

            // Mostrar botón para volver a jugar, ocultar el de siguiente ronda
            btnRondaSiguiente.setVisible(false);
            btnVolverAJugar.setVisible(true);
        } else {
            // Recuadro rojo para la derrota
            resultadoBox.setStyle("-fx-background-color: red; -fx-border-color: #E53935; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 20; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0.5, 0, 0);");

            // Mostrar botón de volver a jugar, ocultar el de siguiente ronda
            btnRondaSiguiente.setVisible(false);
            btnVolverAJugar.setVisible(true);
        }
    }

    private void volverAJugar() {
        this.balatroController.reiniciarJuego();
    }

    private void siguienteRonda(){
        this.balatroController.avanzarRonda();
    }

    /*private void salir() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir del Juego");
        alert.setHeaderText("¿Estás seguro de que deseas salir?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            System.exit(0);
        }
    }*/
    private void salir() {
        Stage stage = new Stage();
        stage.setTitle("Salir del Juego");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label lblMensaje = new Label("¿Estás seguro de que deseas salir?");
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER);

        Button btnSalir = new Button("Salir");
        btnSalir.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-size: 14px;");
        btnSalir.setOnAction(e -> {
            System.exit(0);
        });

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #5bc0de; -fx-text-fill: white; -fx-font-size: 14px;");
        btnCancelar.setOnAction(e -> stage.close());

        botones.getChildren().addAll(btnSalir, btnCancelar);

        root.getChildren().addAll(lblMensaje, botones);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.showAndWait();
    }

    private void aplicarTransicion() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

}