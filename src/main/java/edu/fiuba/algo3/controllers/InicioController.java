package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.Jugador;
import edu.fiuba.algo3.Mazo;
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
import java.nio.file.Paths;
import java.util.Objects;

public class InicioController {

    @FXML
    private TextField nombreTextField;
    @FXML
    private Button jugarButton;
    @FXML
    private MediaView backgroundMediaView;

    private Stage stage;

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
        backgroundMediaView.setMediaPlayer(mediaPlayer);
        jugarButton.setOnAction(event -> iniciarJuego());
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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ronda.fxml"));
            Parent root = loader.load();
            MainController rondaController = loader.getController();
            rondaController.setJugador(new Jugador(nombreJugador, new Mazo()));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("UBAlatro - Ronda");
            rondaController.iniciarRonda();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

