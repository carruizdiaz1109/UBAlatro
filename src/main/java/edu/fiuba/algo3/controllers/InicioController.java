package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Balatro;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Mazo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class InicioController {

    @FXML
    private TextField nombreTextField;
    @FXML
    private Button jugarButton;
    @FXML
    private MediaView backgroundMediaView;

    private Stage stage;
    private BalatroController balatroController;
    private MediaPlayer mediaPlayer;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        String videoPath = Objects.requireNonNull(getClass().getResource("/videos/background.mp4")).toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        reproducirSonidoDeFondo();
        backgroundMediaView.setMediaPlayer(mediaPlayer);
    }

    @FXML
    public void onContinuarClick(ActionEvent event) {
        iniciarJuego();
    }

    @FXML
    private void iniciarJuego() {
        String nombreJugador = nombreTextField.getText();
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Nombre inválido");
            alerta.setHeaderText("Debe ingresar un nombre para jugar.");
            alerta.showAndWait();
            return;
        }
        Balatro balatro = new Balatro(nombreJugador);
        balatroController.setBalatro(balatro);
        balatroController.setStage(stage);
        avanzarRonda();
    }

    public void reproducirSonidoDeFondo() {
        // Ruta del archivo de sonido
        String rutaSonido = getClass().getResource("/sample/sonidoDeFondo.mp3").toExternalForm();
        Media media = new Media(rutaSonido);
        mediaPlayer = new MediaPlayer(media);

        // Configurar el sonido para que se repita en bucle
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Ajustar volumen
        mediaPlayer.setVolume(0.1);

        // Reproducir sonido
        mediaPlayer.play();
    }

    public void setBalatroController(BalatroController balatroController) {
        this.balatroController = balatroController;
    }

    public void avanzarRonda() {
        this.balatroController.inicializarRonda();
    }
}

