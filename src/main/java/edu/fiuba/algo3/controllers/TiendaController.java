package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Jugador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TiendaController {
    private Jugador jugador;
    private Stage stage;

    @FXML
    private Button btnContinuar;

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void clickContinuar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ronda.fxml"));
            Parent root = loader.load();
            RondaController rondaController = loader.getController();
            rondaController.setJugador(jugador);
            rondaController.iniciarRonda();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("UBAlatro - Ronda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

